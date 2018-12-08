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

package com.badlogic.gdx.backends.iosmoe;

import android.util.Log;
import com.badlogic.gdx.utils.Log.LogHandler;
import com.badlogic.gdx.utils.Strings;

public class IOSApplicationLogger extends LogHandler{

    @Override
    public void info(String text, Object... args){
        Log.i(appName, Strings.formatArgs(text, args));
    }

    @Override
    public void warn(String text, Object... args){
        Log.w(appName, Strings.formatArgs(text, args));
    }

    @Override
    public void err(String text, Object... args){
        Log.e(appName, Strings.formatArgs(text, args));
    }
}