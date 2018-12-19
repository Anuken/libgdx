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

package com.badlogic.gdx;

import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.Disposable;

public interface Application extends Disposable{

    /** Returns a list of all the application listeners used. */
    Array<ApplicationListener> getListeners();

    /** Adds a new application listener. */
    default void addListener(ApplicationListener listener){
        synchronized(getListeners()){
            getListeners().add(listener);
        }
    }

    /** Removes an application listener. */
    default void removeListener(ApplicationListener listener){
        synchronized(getListeners()){
            getListeners().remove(listener);
        }
    }

    /** @return what {@link ApplicationType} this application has, e.g. Android or Desktop */
    ApplicationType getType();

    /** @return the Android API level on Android, the major OS version on iOS (5, 6, 7, ..), or 0 on the desktop. */
    int getVersion();

    /** @return the Java heap memory use in bytes */
    long getJavaHeap();

    /** @return the Native heap memory use in bytes */
    long getNativeHeap();

    Clipboard getClipboard();

    /**
     * Posts a runnable on the main loop thread.
     * <p>
     * In a multi-window application, the {@linkplain Core#graphics} and {@linkplain Core#input} values may be
     * unpredictable at the time the Runnable is executed. If graphics or input are needed, they can be copied
     * to a variable to be used in the Runnable. For example:
     * <p><code>
     * final Graphics graphics = Gdx.graphics;
     */
    //TODO move to timer task since posting things isn't really app specific and could run in an update()... somewhere
    void post(Runnable runnable);

    /**
     * Schedule an exit from the application. On android, this will cause a call to pause() and dispose() some time in the future,
     * it will not immediately finish your application.
     * On iOS this should be avoided in production as it breaks Apples guidelines.
     */
    void exit();

    /** Disposes of core resources. */
    @Override
    default void dispose(){
        if(Core.assets != null){
            Core.assets.dispose();
            Core.assets = null;
        }

        if(Core.scene != null){
            Core.scene.dispose();
            Core.scene = null;
        }

        if(Core.atlas != null){
            Core.atlas.dispose();
            Core.atlas = null;
        }
    }

    /** Enumeration of possible {@link Application} types */
    enum ApplicationType{
        Android, Desktop, HeadlessDesktop, WebGL, iOS
    }
}
