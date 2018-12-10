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

package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.service.dreams.DreamService;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.collection.SnapshotArray;
import com.badlogic.gdx.utils.*;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * An implementation of the {@link Application} interface for Android. Create an {@link Activity} that derives from this class. In
 * the Activity#onCreate(Bundle) method call the {@link #initialize(ApplicationListener)} method specifying the configuration for
 * the {@link GLSurfaceView}.
 *
 * @author mzechner
 */
public class AndroidDaydream extends DreamService implements AndroidApplicationBase{
    static{
        GdxNativesLoader.load();
    }

    protected AndroidGraphics graphics;
    protected AndroidInput input;
    protected AndroidAudio audio;
    protected AndroidFiles files;
    protected AndroidNet net;
    protected AndroidClipboard clipboard;
    protected ApplicationListener listener;
    protected Handler handler;
    protected boolean firstResume = true;
    protected final Array<Runnable> runnables = new Array<Runnable>();
    protected final Array<Runnable> executedRunnables = new Array<Runnable>();
    protected final SnapshotArray<LifecycleListener> lifecycleListeners = new SnapshotArray<LifecycleListener>(LifecycleListener.class);

    /**
     * This method has to be called in the Activity#onCreate(Bundle) method. It sets up all the things necessary to get input,
     * render via OpenGL and so on. Uses a default {@link AndroidApplicationConfiguration}.
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     */
    public void initialize(ApplicationListener listener){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(listener, config);
    }

    /**
     * This method has to be called in the Activity#onCreate(Bundle) method. It sets up all the things necessary to get input,
     * render via OpenGL and so on. You can configure other aspects of the application with the rest of the fields in the
     * {@link AndroidApplicationConfiguration} instance.
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     * @param config the {@link AndroidApplicationConfiguration}, defining various settings of the application (use accelerometer,
     * etc.).
     */
    public void initialize(ApplicationListener listener, AndroidApplicationConfiguration config){
        init(listener, config, false);
    }

    /**
     * This method has to be called in the Activity#onCreate(Bundle) method. It sets up all the things necessary to get input,
     * render via OpenGL and so on. Uses a default {@link AndroidApplicationConfiguration}.
     * <p>
     * Note: you have to add the returned view to your layout!
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     * @return the {@link GLSurfaceView} of the application
     */
    public View initializeForView(ApplicationListener listener){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        return initializeForView(listener, config);
    }

    /**
     * This method has to be called in the Activity#onCreate(Bundle) method. It sets up all the things necessary to get input,
     * render via OpenGL and so on. You can configure other aspects of the application with the rest of the fields in the
     * {@link AndroidApplicationConfiguration} instance.
     * <p>
     * Note: you have to add the returned view to your layout!
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     * @param config the {@link AndroidApplicationConfiguration}, defining various settings of the application (use accelerometer,
     * etc.).
     * @return the {@link GLSurfaceView} of the application
     */
    public View initializeForView(ApplicationListener listener, AndroidApplicationConfiguration config){
        init(listener, config, true);
        return graphics.getView();
    }

    private void init(ApplicationListener listener, AndroidApplicationConfiguration config, boolean isForView){
        Log.setLogger(new AndroidApplicationLogger());
        graphics = new AndroidGraphics(this, config, config.resolutionStrategy == null ? new FillResolutionStrategy()
        : config.resolutionStrategy);
        input = AndroidInputFactory.newAndroidInput(this, this, graphics.view, config);
        audio = new AndroidAudio(this, config);
        this.getFilesDir(); // workaround for Android bug #10515463
        files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
        net = new AndroidNet(this);
        this.listener = listener;
        this.handler = new Handler();
        this.clipboard = new AndroidClipboard(this);

        // Add a specialized audio lifecycle listener
        addLifecycleListener(new LifecycleListener(){

            @Override
            public void resume(){
                audio.resume();
            }

            @Override
            public void pause(){
                audio.pause();
            }

            @Override
            public void dispose(){
                audio.dispose();
                audio = null;
            }
        });

        Core.app = this;
        Core.input = input;
        Core.audio = audio;
        Core.files = files;
        Core.graphics = graphics;
        Core.net = net;

        if(!isForView){
            setFullscreen(true);
            setContentView(graphics.getView(), createLayoutParams());
        }

        createWakeLock(config.useWakelock);
        hideStatusBar(config);
    }

    protected FrameLayout.LayoutParams createLayoutParams(){
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
        android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    protected void createWakeLock(boolean use){
        if(use){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    protected void hideStatusBar(AndroidApplicationConfiguration config){
        if(!config.hideStatusBar || getVersion() < 11) return;

        View rootView = getWindow().getDecorView();

        try{
            Method m = View.class.getMethod("setSystemUiVisibility", int.class);
            m.invoke(rootView, 0x0);
            m.invoke(rootView, 0x1);
        }catch(Exception e){
            Log.info("[AndroidApplication] Can't hide status bar", e);
        }
    }

    @Override
    public void onDreamingStopped(){
        boolean isContinuous = graphics.isContinuousRendering();
        graphics.setContinuousRendering(true);
        graphics.pause();

        input.unregisterSensorListeners();

        int[] realId = input.realId;
        // erase pointer ids. this sucks donkeyballs...
        Arrays.fill(realId, -1);
        boolean[] touched = input.touched;
        // erase touched state. this also sucks donkeyballs...
        Arrays.fill(touched, false);
        graphics.clearManagedCaches();
        graphics.destroy();
        graphics.setContinuousRendering(isContinuous);

        graphics.onPauseGLSurfaceView();

        super.onDreamingStopped();
    }

    @Override
    public void onDreamingStarted(){
        Core.app = this;
        Core.input = input;
        Core.audio = audio;
        Core.files = files;
        Core.graphics = graphics;
        Core.net = net;

        input.registerSensorListeners();

        if(graphics != null){
            graphics.onResumeGLSurfaceView();
        }

        if(!firstResume){
            graphics.resume();
        }else
            firstResume = false;
        super.onDreamingStarted();
    }

    @Override
    public void onDetachedFromWindow(){
        super.onDetachedFromWindow();
    }

    @Override
    public ApplicationListener getApplicationListener(){
        return listener;
    }

    @Override
    public ApplicationType getType(){
        return ApplicationType.Android;
    }

    @Override
    public int getVersion(){
        return android.os.Build.VERSION.SDK_INT;
    }

    @Override
    public long getJavaHeap(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getNativeHeap(){
        return Debug.getNativeHeapAllocatedSize();
    }

    @Override
    public Preferences getPreferences(String name){
        return new AndroidPreferences(getSharedPreferences(name, Context.MODE_PRIVATE));
    }

    @Override
    public Clipboard getClipboard(){
        return clipboard;
    }

    @Override
    public void post(Runnable runnable){
        synchronized(runnables){
            runnables.add(runnable);
            Core.graphics.requestRendering();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration config){
        super.onConfigurationChanged(config);
        boolean keyboardAvailable = false;
        if(config.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) keyboardAvailable = true;
        input.keyboardAvailable = keyboardAvailable;
    }

    @Override
    public void exit(){
        handler.post(new Runnable(){
            @Override
            public void run(){
                AndroidDaydream.this.finish();
            }
        });
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

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    public Array<Runnable> getRunnables(){
        return runnables;
    }

    @Override
    public Array<Runnable> getExecutedRunnables(){
        return executedRunnables;
    }

    @Override
    public SnapshotArray<LifecycleListener> getLifecycleListeners(){
        return lifecycleListeners;
    }

    @Override
    public Window getApplicationWindow(){
        return this.getWindow();
    }

    @Override
    public Handler getHandler(){
        return this.handler;
    }

    @Override
    public void runOnUiThread(Runnable runnable){
        if(Looper.myLooper() != Looper.getMainLooper()){
            // The current thread is not the UI thread.
            // Let's post the runnable to the event queue of the UI thread.
            new Handler(Looper.getMainLooper()).post(runnable);
        }else{
            // The current thread is the UI thread already.
            // Let's execute the runnable immediately.
            runnable.run();
        }
    }

    @Override
    public void useImmersiveMode(boolean b){
        throw new UnsupportedOperationException();
    }
}
