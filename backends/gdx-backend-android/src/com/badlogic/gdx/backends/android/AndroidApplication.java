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

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Core;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Log;

import java.lang.reflect.Method;

/**
 * An implementation of the {@link Application} interface for Android. Create an {@link Activity} that derives from this class. In
 * the {@link Activity#onCreate(Bundle)} method call the {@link #initialize(ApplicationListener)} method specifying the
 * configuration for the GLSurfaceView.
 *
 * @author mzechner
 */
public class AndroidApplication extends Activity implements AndroidApplicationBase{
    protected AndroidGraphics graphics;
    protected AndroidInput input;
    protected AndroidAudio audio;
    protected AndroidFiles files;
    protected AndroidNet net;
    protected AndroidClipboard clipboard;
    protected ApplicationListener listener;
    public Handler handler;
    protected boolean firstResume = true;
    protected final Array<ApplicationListener> listeners = new Array<>();
    protected final Array<Runnable> runnables = new Array<>();
    protected final Array<Runnable> executedRunnables = new Array<>();
    private final Array<AndroidEventListener> androidEventListeners = new Array<>();
    protected boolean useImmersiveMode = false;
    protected boolean hideStatusBar = false;
    private int wasFocusChanged = -1;
    private boolean isWaitingForAudio = false;

    static{
        GdxNativesLoader.load();
        Log.setLogger(new AndroidApplicationLogger());
    }

    /**
     * This method has to be called in the {@link Activity#onCreate(Bundle)} method. It sets up all the things necessary to get
     * input, render via OpenGL and so on. Uses a default {@link AndroidApplicationConfiguration}.
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     **/
    public void initialize(ApplicationListener listener){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(listener, config);
    }

    /**
     * This method has to be called in the {@link Activity#onCreate(Bundle)} method. It sets up all the things necessary to get
     * input, render via OpenGL and so on. You can configure other aspects of the application with the rest of the fields in the
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
     * This method has to be called in the {@link Activity#onCreate(Bundle)} method. It sets up all the things necessary to get
     * input, render via OpenGL and so on. Uses a default {@link AndroidApplicationConfiguration}.
     * <p>
     * Note: you have to add the returned view to your layout!
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     * @return the GLSurfaceView of the application
     */
    public View initializeForView(ApplicationListener listener){
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        return initializeForView(listener, config);
    }

    /**
     * This method has to be called in the {@link Activity#onCreate(Bundle)} method. It sets up all the things necessary to get
     * input, render via OpenGL and so on. You can configure other aspects of the application with the rest of the fields in the
     * {@link AndroidApplicationConfiguration} instance.
     * <p>
     * Note: you have to add the returned view to your layout!
     *
     * @param listener the {@link ApplicationListener} implementing the program logic
     * @param config the {@link AndroidApplicationConfiguration}, defining various settings of the application (use accelerometer,
     * etc.).
     * @return the GLSurfaceView of the application
     */
    public View initializeForView(ApplicationListener listener, AndroidApplicationConfiguration config){
        init(listener, config, true);
        return graphics.getView();
    }

    private void init(ApplicationListener listener, AndroidApplicationConfiguration config, boolean isForView){
        if(this.getVersion() < MINIMUM_SDK){
            throw new GdxRuntimeException("LibGDX requires Android API Level " + MINIMUM_SDK + " or later.");
        }
        graphics = new AndroidGraphics(this, config, config.resolutionStrategy == null ? new FillResolutionStrategy()
        : config.resolutionStrategy);
        input = AndroidInputFactory.newAndroidInput(this, this, graphics.view, config);
        audio = new AndroidAudio(this, config);
        this.getFilesDir(); // workaround for Android bug #10515463
        files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
        net = new AndroidNet(this);
        this.listener = listener;
        this.handler = new Handler();
        this.useImmersiveMode = config.useImmersiveMode;
        this.hideStatusBar = config.hideStatusBar;
        this.clipboard = new AndroidClipboard(this);

        // Add a specialized audio lifecycle listener
        addListener(new ApplicationListener(){
            @Override
            public void pause(){
                audio.pause();
            }

            @Override
            public void dispose(){
                audio.dispose();
            }
        });

        Core.app = this;
        Core.input = input;
        Core.audio = audio;
        Core.files = files;
        Core.graphics = graphics;
        Core.net = net;

        if(!isForView){
            try{
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }catch(Exception ex){
                Log.err("[AndroidApplication] Content already displayed, cannot request FEATURE_NO_TITLE", ex);
            }
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            setContentView(graphics.getView(), createLayoutParams());
        }

        createWakeLock(config.useWakelock);
        hideStatusBar(this.hideStatusBar);
        useImmersiveMode(this.useImmersiveMode);
        if(this.useImmersiveMode && getVersion() >= Build.VERSION_CODES.KITKAT){
            try{
                Class<?> vlistener = Class.forName("com.badlogic.gdx.backends.android.AndroidVisibilityListener");
                Object o = vlistener.newInstance();
                Method method = vlistener.getDeclaredMethod("createListener", AndroidApplicationBase.class);
                method.invoke(o, this);
            }catch(Exception e){
                Log.err("[AndroidApplication] Failed to create AndroidVisibilityListener", e);
            }
        }

        // detect an already connected bluetooth keyboardAvailable
        if(getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS){
            input.keyboardAvailable = true;
        }
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

    protected void hideStatusBar(boolean hide){
        if(!hide || getVersion() < 11) return;

        View rootView = getWindow().getDecorView();

        try{
            Method m = View.class.getMethod("setSystemUiVisibility", int.class);
            if(getVersion() <= 13) m.invoke(rootView, 0x0);
            m.invoke(rootView, 0x1);
        }catch(Exception e){
            Log.err("[AndroidApplication] Can't hide status bar", e);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        useImmersiveMode(this.useImmersiveMode);
        hideStatusBar(this.hideStatusBar);
        if(hasFocus){
            this.wasFocusChanged = 1;
            if(this.isWaitingForAudio){
                this.audio.resume();
                this.isWaitingForAudio = false;
            }
        }else{
            this.wasFocusChanged = 0;
        }
    }

    @TargetApi(19)
    @Override
    public void useImmersiveMode(boolean use){
        if(!use || getVersion() < Build.VERSION_CODES.KITKAT) return;

        View view = getWindow().getDecorView();
        try{
            Method m = View.class.getMethod("setSystemUiVisibility", int.class);
            int code = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            m.invoke(view, code);
        }catch(Exception e){
            Log.err("[AndroidApplication] Can't set immersive mode", e);
        }
    }

    @Override
    protected void onPause(){
        boolean isContinuous = graphics.isContinuousRendering();
        boolean isContinuousEnforced = AndroidGraphics.enforceContinuousRendering;

        // from here we don't want non continuous rendering
        AndroidGraphics.enforceContinuousRendering = true;
        graphics.setContinuousRendering(true);
        // calls to setContinuousRendering(false) from other thread (ex: GLThread)
        // will be ignored at this point...
        graphics.pause();

        input.onPause();

        if(isFinishing()){
            graphics.clearManagedCaches();
            graphics.destroy();
        }

        AndroidGraphics.enforceContinuousRendering = isContinuousEnforced;
        graphics.setContinuousRendering(isContinuous);

        graphics.onPauseGLSurfaceView();

        super.onPause();
    }

    @Override
    protected void onResume(){
        Core.app = this;
        Core.input = input;
        Core.audio = audio;
        Core.files = files;
        Core.graphics = graphics;
        Core.net = net;

        input.onResume();

        if(graphics != null){
            graphics.onResumeGLSurfaceView();
        }

        if(!firstResume){
            graphics.resume();
        }else
            firstResume = false;

        this.isWaitingForAudio = true;
        if(this.wasFocusChanged == 1 || this.wasFocusChanged == -1){
            this.audio.resume();
            this.isWaitingForAudio = false;
        }
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
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
        handler.post(AndroidApplication.this::finish);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // forward events to our listeners if there are any installed
        synchronized(androidEventListeners){
            for(int i = 0; i < androidEventListeners.size; i++){
                androidEventListeners.get(i).onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    /** Adds an event listener for Android specific event such as onActivityResult(...). */
    public void addAndroidEventListener(AndroidEventListener listener){
        synchronized(androidEventListeners){
            androidEventListeners.add(listener);
        }
    }

    /** Removes an event listener for Android specific event such as onActivityResult(...). */
    public void removeAndroidEventListener(AndroidEventListener listener){
        synchronized(androidEventListeners){
            androidEventListeners.removeValue(listener, true);
        }
    }

    @Override
    public Array<ApplicationListener> getListeners(){
        return listeners;
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
    public Window getApplicationWindow(){
        return this.getWindow();
    }

    @Override
    public Handler getHandler(){
        return this.handler;
    }
}
