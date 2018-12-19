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

package com.badlogic.gdx.backends.headless;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Core;
import com.badlogic.gdx.Settings;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;
import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * a headless implementation of a GDX Application primarily intended to be used in servers
 *
 * @author Jon Renner
 */
public class HeadlessApplication implements Application{
    protected Thread mainLoopThread;
    protected final HeadlessFiles files;
    protected final HeadlessNet net;
    protected final MockAudio audio;
    protected final MockInput input;
    protected final MockGraphics graphics;
    protected boolean running = true;
    protected final Array<ApplicationListener> listeners = new Array<>();
    protected final Array<Runnable> runnables = new Array<>();
    protected final Array<Runnable> executedRunnables = new Array<>();
    private final long renderInterval;

    public HeadlessApplication(ApplicationListener listener){
        this(listener, null);
    }

    public HeadlessApplication(ApplicationListener listener, HeadlessApplicationConfiguration config){
        if(config == null)
            config = new HeadlessApplicationConfiguration();

        addListener(listener);
        this.files = new HeadlessFiles();
        this.net = new HeadlessNet();
        // the following elements are not applicable for headless applications
        // they are only implemented as mock objects
        this.graphics = new MockGraphics();
        this.audio = new MockAudio();
        this.input = new MockInput();

        Core.settings = new Settings();
        Core.app = this;
        Core.files = files;
        Core.net = net;
        Core.audio = audio;
        Core.graphics = graphics;
        Core.input = input;

        renderInterval = config.renderInterval > 0 ? (long) (config.renderInterval * 1000000000f) : (config.renderInterval < 0 ? -1 : 0);

        initialize();
    }

    private void initialize(){
        mainLoopThread = new Thread("HeadlessApplication"){
            @Override
            public void run(){
                try{
                    HeadlessApplication.this.mainLoop();
                }catch(Throwable t){
                    if(t instanceof RuntimeException)
                        throw (RuntimeException) t;
                    else
                        throw new GdxRuntimeException(t);
                }
            }
        };
        mainLoopThread.start();
    }

    void mainLoop(){
        synchronized(listeners){
            for(ApplicationListener listener : listeners){
                listener.create();
            }
        }

        // unlike LwjglApplication, a headless application will eat up CPU in this while loop
        // it is up to the implementation to call Thread.sleep as necessary
        long t = TimeUtils.nanoTime() + renderInterval;
        if(renderInterval >= 0f){
            while(running){
                final long n = TimeUtils.nanoTime();
                if(t > n){
                    try{
                        Thread.sleep((t - n) / 1000000);
                    }catch(InterruptedException e){
                    }
                    t = TimeUtils.nanoTime() + renderInterval;
                }else
                    t = n + renderInterval;

                executeRunnables();
                graphics.incrementFrameId();
                synchronized(listeners){
                    for(ApplicationListener listener : listeners){
                        listener.update();
                    }
                }
                graphics.updateTime();

                // If one of the runnables set running to false, for example after an exit().
                if(!running) break;
            }
        }

        synchronized(listeners){
            for(ApplicationListener listener : listeners){
                listener.pause();
                listener.dispose();
            }
        }
    }

    public boolean executeRunnables(){
        synchronized(runnables){
            for(int i = runnables.size - 1; i >= 0; i--)
                executedRunnables.add(runnables.get(i));
            runnables.clear();
        }
        if(executedRunnables.size == 0) return false;
        for(int i = executedRunnables.size - 1; i >= 0; i--)
            executedRunnables.removeAt(i).run();
        return true;
    }

    @Override
    public ApplicationType getType(){
        return ApplicationType.HeadlessDesktop;
    }

    @Override
    public int getVersion(){
        return 0;
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
    public Clipboard getClipboard(){
        // no clipboards for headless apps
        return null;
    }

    @Override
    public Array<ApplicationListener> getListeners(){
        return listeners;
    }

    @Override
    public void post(Runnable runnable){
        synchronized(runnables){
            runnables.add(runnable);
        }
    }

    @Override
    public void exit(){
        post(() -> running = false);
    }
}
