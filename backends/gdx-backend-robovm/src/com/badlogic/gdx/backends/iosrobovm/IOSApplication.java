/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.backends.iosrobovm;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.iosrobovm.objectal.OALAudioSession;
import com.badlogic.gdx.backends.iosrobovm.objectal.OALSimpleAudio;
import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.Log;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSMutableDictionary;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.NSThread;
import org.robovm.apple.uikit.*;
import org.robovm.rt.bro.Bro;

import java.io.File;

public class IOSApplication implements Application{

    public static abstract class Delegate extends UIApplicationDelegateAdapter{
        private IOSApplication app;

        protected abstract IOSApplication createApplication();

        @Override
        public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions){
            application.addStrongRef(this); // Prevent this from being GCed until the ObjC UIApplication is deallocated
            this.app = createApplication();
            return app.didFinishLaunching(application, launchOptions);
        }

        @Override
        public void didBecomeActive(UIApplication application){
            app.didBecomeActive(application);
        }

        @Override
        public void willEnterForeground(UIApplication application){
            app.willEnterForeground(application);
        }

        @Override
        public void willResignActive(UIApplication application){
            app.willResignActive(application);
        }

        @Override
        public void willTerminate(UIApplication application){
            app.willTerminate(application);
        }
    }

    UIApplication uiApp;
    UIWindow uiWindow;
    ApplicationListener listener;
    IOSViewControllerListener viewControllerListener;
    IOSApplicationConfiguration config;
    IOSGraphics graphics;
    IOSAudio audio;
    IOSFiles files;
    IOSInput input;
    IOSNet net;
    int logLevel = Application.LOG_DEBUG;

    /** The display scale factor (1.0f for normal; 2.0f to use retina coordinates/dimensions). */
    float displayScaleFactor;

    private CGRect lastScreenBounds = null;

    Array<Runnable> runnables = new Array<Runnable>();
    Array<Runnable> executedRunnables = new Array<Runnable>();
    Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();

    public IOSApplication(ApplicationListener listener, IOSApplicationConfiguration config){
        this.listener = listener;
        this.config = config;
    }

    final boolean didFinishLaunching(UIApplication uiApp, UIApplicationLaunchOptions options){
        Log.setLogger(new IOSApplicationLogger());
        Core.app = this;
        this.uiApp = uiApp;

        // enable or disable screen dimming
        UIApplication.getSharedApplication().setIdleTimerDisabled(config.preventScreenDimming);

        Log.info("[IOSApplication] iOS version: " + UIDevice.getCurrentDevice().getSystemVersion());
        // fix the scale factor if we have a retina device (NOTE: iOS screen sizes are in "points" not pixels by default!)

        Log.info("[IOSApplication] Running in " + (Bro.IS_64BIT ? "64-bit" : "32-bit") + " mode");

        float scale = (float) (getIosVersion() >= 8 ? UIScreen.getMainScreen().getNativeScale() : UIScreen.getMainScreen()
        .getScale());
        if(scale >= 2.0f){
            Log.info("[IOSApplication] scale: " + scale);
            if(UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.Pad){
                // it's an iPad!
                displayScaleFactor = config.displayScaleLargeScreenIfRetina * scale;
            }else{
                // it's an iPod or iPhone
                displayScaleFactor = config.displayScaleSmallScreenIfRetina * scale;
            }
        }else{
            // no retina screen: no scaling!
            if(UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.Pad){
                // it's an iPad!
                displayScaleFactor = config.displayScaleLargeScreenIfNonRetina;
            }else{
                // it's an iPod or iPhone
                displayScaleFactor = config.displayScaleSmallScreenIfNonRetina;
            }
        }

        // setup libgdx
        this.input = createInput();
        this.graphics = createGraphics(scale);
        Core.gl = Core.gl20 = graphics.gl20;
        Core.gl30 = graphics.gl30;
        this.files = new IOSFiles();
        this.audio = new IOSAudio(config);
        this.net = new IOSNet(this);

        Core.files = this.files;
        Core.graphics = this.graphics;
        Core.audio = this.audio;
        Core.input = this.input;
        Core.net = this.net;

        this.input.setupPeripherals();

        this.uiWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        this.uiWindow.setRootViewController(this.graphics.viewController);
        this.uiWindow.makeKeyAndVisible();
        Log.info("[IOSApplication] created");
        return true;
    }

    protected IOSGraphics createGraphics(float scale){
        return new IOSGraphics(scale, this, config, input, config.useGL30);
    }

    protected IOSInput createInput(){
        return new IOSInput(this);
    }

    int getIosVersion(){
        String systemVersion = UIDevice.getCurrentDevice().getSystemVersion();
        int version = Integer.parseInt(systemVersion.split("\\.")[0]);
        return version;
    }

    /**
     * Return the UI view controller of IOSApplication
     *
     * @return the view controller of IOSApplication
     */
    public UIViewController getUIViewController(){
        return graphics.viewController;
    }

    /**
     * Return the UI Window of IOSApplication
     *
     * @return the window
     */
    public UIWindow getUIWindow(){
        return uiWindow;
    }

    /**
     * GL View spans whole screen, that is, even under the status bar. iOS can also rotate the screen, which is not handled
     * consistently over iOS versions. This method returns, in pixels, rectangle in which libGDX draws.
     *
     * @return dimensions of space we draw to, adjusted for device orientation
     */
    protected CGRect getBounds(){
        final CGRect screenBounds = UIScreen.getMainScreen().getBounds();
        final CGRect statusBarFrame = uiApp.getStatusBarFrame();
        final UIInterfaceOrientation statusBarOrientation = uiApp.getStatusBarOrientation();

        double statusBarHeight = Math.min(statusBarFrame.getWidth(), statusBarFrame.getHeight());

        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Make sure that the orientation is consistent with ratios. Should be, but may not be on older iOS versions
        switch(statusBarOrientation){
            case LandscapeLeft:
            case LandscapeRight:
                if(screenHeight > screenWidth){
                    Log.info("[IOSApplication] Switching reported width and height (w=" + screenWidth + " h=" + screenHeight + ")");
                    double tmp = screenHeight;
                    // noinspection SuspiciousNameCombination
                    screenHeight = screenWidth;
                    screenWidth = tmp;
                }
        }

        // update width/height depending on display scaling selected
        screenWidth *= displayScaleFactor;
        screenHeight *= displayScaleFactor;

        if(statusBarHeight != 0.0){
            Log.info("[IOSApplication] Status bar is visible (height = " + statusBarHeight + ")");
            statusBarHeight *= displayScaleFactor;
            screenHeight -= statusBarHeight;
        }else{
            Log.info("[IOSApplication] Status bar is not visible");
        }

        Log.info("[IOSApplication] Total computed bounds are w=" + screenWidth + " h=" + screenHeight);

        return lastScreenBounds = new CGRect(0.0, statusBarHeight, screenWidth, screenHeight);
    }

    protected CGRect getCachedBounds(){
        if(lastScreenBounds == null)
            return getBounds();
        else
            return lastScreenBounds;
    }

    final void didBecomeActive(UIApplication uiApp){
        Log.info("[IOSApplication] resumed");
        // workaround for ObjectAL crash problem
        // see: https://groups.google.com/forum/?fromgroups=#!topic/objectal-for-iphone/ubRWltp_i1Q
        OALAudioSession audioSession = OALAudioSession.sharedInstance();
        if(audioSession != null){
            audioSession.forceEndInterruption();
        }
        if(config.allowIpod){
            OALSimpleAudio audio = OALSimpleAudio.sharedInstance();
            if(audio != null){
                audio.setUseHardwareIfAvailable(false);
            }
        }
        graphics.makeCurrent();
        graphics.resume();
    }

    final void willEnterForeground(UIApplication uiApp){
        // workaround for ObjectAL crash problem
        // see: https://groups.google.com/forum/?fromgroups=#!topic/objectal-for-iphone/ubRWltp_i1Q
        OALAudioSession audioSession = OALAudioSession.sharedInstance();
        if(audioSession != null){
            audioSession.forceEndInterruption();
        }
    }

    final void willResignActive(UIApplication uiApp){
        Log.info("[IOSApplication] paused");
        graphics.makeCurrent();
        graphics.pause();
        Core.gl.glFinish();
    }

    final void willTerminate(UIApplication uiApp){
        Log.info("[IOSApplication] disposed");
        graphics.makeCurrent();
        Array<LifecycleListener> listeners = lifecycleListeners;
        synchronized(listeners){
            for(LifecycleListener listener : listeners){
                listener.pause();
            }
        }
        listener.dispose();
        Core.gl.glFinish();
    }

    @Override
    public ApplicationListener getApplicationListener(){
        return listener;
    }

    @Override
    public Graphics getGraphics(){
        return graphics;
    }

    @Override
    public Audio getAudio(){
        return audio;
    }

    @Override
    public Input getInput(){
        return input;
    }

    @Override
    public Files getFiles(){
        return files;
    }

    @Override
    public Net getNet(){
        return net;
    }

    @Override
    public ApplicationType getType(){
        return ApplicationType.iOS;
    }

    @Override
    public int getVersion(){
        return Integer.parseInt(UIDevice.getCurrentDevice().getSystemVersion().split("\\.")[0]);
    }

    @Override
    public long getJavaHeap(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getNativeHeap(){
        return getJavaHeap();
    }

    @Override
    public Preferences getPreferences(String name){
        File libraryPath = new File(System.getenv("HOME"), "Library");
        File finalPath = new File(libraryPath, name + ".plist");

        @SuppressWarnings("unchecked")
        NSMutableDictionary<NSString, NSObject> nsDictionary = (NSMutableDictionary<NSString, NSObject>) NSMutableDictionary
        .read(finalPath);

        // if it fails to get an existing dictionary, create a new one.
        if(nsDictionary == null){
            nsDictionary = new NSMutableDictionary<NSString, NSObject>();
            nsDictionary.write(finalPath, false);
        }
        return new IOSPreferences(nsDictionary, finalPath.getAbsolutePath());
    }

    @Override
    public void post(Runnable runnable){
        synchronized(runnables){
            runnables.add(runnable);
            Core.graphics.requestRendering();
        }
    }

    public void processRunnables(){
        synchronized(runnables){
            executedRunnables.clear();
            executedRunnables.addAll(runnables);
            runnables.clear();
        }
        for(int i = 0; i < executedRunnables.size; i++){
            try{
                executedRunnables.get(i).run();
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    @Override
    public void exit(){
        NSThread.exit();
    }

    @Override
    public Clipboard getClipboard(){
        return new Clipboard(){
            @Override
            public void setContents(String content){
                UIPasteboard.getGeneralPasteboard().setString(content);
            }

            @Override
            public String getContents(){
                return UIPasteboard.getGeneralPasteboard().getString();
            }
        };
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener){
        synchronized(lifecycleListeners){
            lifecycleListeners.add(listener);
        }
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener){
        synchronized(lifecycleListeners){
            lifecycleListeners.removeValue(listener, true);
        }
    }

    /**
     * Add a listener to handle events from the libgdx root view controller
     *
     * @param listener The {#link IOSViewControllerListener} to add
     */
    public void addViewControllerListener(IOSViewControllerListener listener){
        viewControllerListener = listener;
    }
}
