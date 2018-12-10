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

import com.badlogic.gdx.KeyBinds.Axis;
import com.badlogic.gdx.KeyBinds.KeyBind;
import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.function.Consumer;
import com.badlogic.gdx.input.*;

import static com.badlogic.gdx.Core.keybinds;

/**
 * <p>
 * Interface to the input facilities. This allows polling the state of the keyboard, the touch screen and the accelerometer. On
 * some backends (desktop, gwt, etc) the touch screen is replaced by mouse input. The accelerometer is of course not available on
 * all backends.
 * </p>
 *
 * <p>
 * The class also offers methods to use (and test for the presence of) other input systems like vibration, compass, on-screen
 * keyboards, and cursor capture. Support for simple input dialogs is also provided.
 * </p>
 *
 * @author mzechner
 */
public abstract class Input{
    /**Controller stick deadzone.*/
    private final static float deadzone = 0.3f;
    /**The default input device (keyboard)*/
    private KeyboardDevice keyboard = new KeyboardDevice();
    /**All available input devices, including controllers and keybowards.*/
    private Array<InputDevice> devices = Array.with(keyboard);
    /**An input multiplexer to handle events.*/
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();

    /** @return The acceleration force in m/s^2 applied to the device in the X axis, including the force of gravity */
    public abstract float getAccelerometerX();

    /** @return The acceleration force in m/s^2 applied to the device in the Y axis, including the force of gravity */
    public abstract float getAccelerometerY();

    /** @return The acceleration force in m/s^2 applied to the device in the Z axis, including the force of gravity */
    public abstract float getAccelerometerZ();

    /** @return The rate of rotation in rad/s around the X axis */
    public abstract float getGyroscopeX();

    /** @return The rate of rotation in rad/s around the Y axis */
    public abstract float getGyroscopeY();

    /** @return The rate of rotation in rad/s around the Z axis */
    public abstract float getGyroscopeZ();

    /**
     * @return The x coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first
     * pointer in screen coordinates. The screen origin is the top left corner.
     */
    public abstract int mouseX();

    /**
     * Returns the x coordinate in screen coordinates of the given pointer. Pointers are indexed from 0 to n. The pointer id
     * identifies the order in which the fingers went down on the screen, e.g. 0 is the first finger, 1 is the second and so on.
     * When two fingers are touched down and the first one is lifted the second one keeps its index. If another finger is placed on
     * the touch screen the first free index will be used.
     *
     * @param pointer the pointer id.
     * @return the x coordinate
     */
    public abstract int mouseX(int pointer);

    /** @return the different between the current pointer location and the last pointer location on the x-axis. */
    public abstract int deltaX();

    /** @return the different between the current pointer location and the last pointer location on the x-axis. */
    public abstract int deltaX(int pointer);

    /**
     * @return The y coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first
     * pointer in screen coordinates. The screen origin is the bottom left corner.
     */
    public abstract int mouseY();

    /**
     * Returns the y coordinate in screen coordinates of the given pointer. Pointers are indexed from 0 to n. The pointer id
     * identifies the order in which the fingers went down on the screen, e.g. 0 is the first finger, 1 is the second and so on.
     * When two fingers are touched down and the first one is lifted the second one keeps its index. If another finger is placed on
     * the touch screen the first free index will be used.
     *
     * @param pointer the pointer id.
     * @return the y coordinate
     */
    public abstract int mouseY(int pointer);

    /** @return the different between the current pointer location and the last pointer location on the y-axis. */
    public abstract int deltaY();

    /** @return the different between the current pointer location and the last pointer location on the y-axis. */
    public abstract int deltaY(int pointer);

    /** @return whether the screen is currently touched. */
    public abstract boolean isTouched();

    /** @return whether a new touch down event just occurred. */
    public abstract boolean justTouched();

    /**
     * Whether the screen is currently touched by the pointer with the given index. Pointers are indexed from 0 to n. The pointer
     * id identifies the order in which the fingers went down on the screen, e.g. 0 is the first finger, 1 is the second and so on.
     * When two fingers are touched down and the first one is lifted the second one keeps its index. If another finger is placed on
     * the touch screen the first free index will be used.
     *
     * @param pointer the pointer
     * @return whether the screen is touched by the pointer
     */
    public abstract boolean isTouched(int pointer);

    /** @return the pressure of the first pointer */
    public abstract float getPressure();

    /**
     * Returns the pressure of the given pointer, where 0 is untouched. On Android it should be
     * up to 1.0, but it can go above that slightly and its not consistent between devices. On iOS 1.0 is the normal touch
     * and significantly more of hard touch. Check relevant manufacturer documentation for details.
     * Check availability with {@link Input#isPeripheralAvailable(Peripheral)}. If not supported, returns 1.0 when touched.
     *
     * @param pointer the pointer id.
     * @return the pressure
     */
    public abstract float getPressure(int pointer);

    /**Returns whether the key is pressed.*/
    public boolean isKeyPressed(KeyCode key){
        return keyboard.isKeyPressed(key);
    }

    /**Returns whether the key has just been pressed.*/
    public boolean isKeyTapped(KeyCode key){
        return keyboard.isKeyTapped(key);
    }

    /**Returns whether the key has just been released.*/
    public boolean isKeyReleased(KeyCode key){
        return keyboard.isKeyReleased(key);
    }

    /**Returns the [-1, 1] axis value of a key.*/
    public float getAxis(KeyCode key){
        return keyboard.getAxis(key);
    }

    /**Returns whether the keybind is pressed.*/
    public boolean isKeyPressed(KeyBind key){
        return keybinds.get(key).key != null && keyboard.isKeyPressed(keybinds.get(key).key);
    }

    /**Returns whether the key has just been pressed.*/
    public boolean isKeyTapped(KeyBind key){
        return keybinds.get(key).key != null && keyboard.isKeyTapped(keybinds.get(key).key);
    }

    /**Returns whether the key has just been released.*/
    public boolean isKeyReleased(KeyBind key){
        return keybinds.get(key).key != null && keyboard.isKeyReleased(keybinds.get(key).key);
    }

    /**Returns the [-1, 1] axis value of a key.*/
    public float getAxis(KeyBind key){
        Axis axis = keybinds.get(key);
        if(axis.key != null){
            return keyboard.getAxis(axis.key);
        }else{
            return keyboard.isKeyTapped(axis.min) ? -1 : keyboard.isKeyTapped(axis.max) ? 1 : 0;
        }
    }

    /**
     * System dependent method to input a string of text. A dialog box will be created with the given title and the given text as a
     * message for the user. Once the dialog has been closed the consumer be called on the rendering thread.
     *
     * @param listener The TextInputListener.
     * @param title The title of the text input dialog.
     * @param text The message presented to the user.
     */
    public abstract void getTextInput(Consumer<String> listener, String title, String text, String hint);

    /**
     * Sets the on-screen keyboard visible if available.
     *
     * @param visible visible or not
     */
    public abstract void setOnscreenKeyboardVisible(boolean visible);

    /**
     * Vibrates for the given amount of time. Note that you'll need the permission
     * <code> <uses-permission android:name="android.permission.VIBRATE" /></code> in your manifest file in order for this to work.
     *
     * @param milliseconds the number of milliseconds to vibrate.
     */
    public abstract void vibrate(int milliseconds);

    /**
     * Vibrate with a given pattern. Pass in an array of ints that are the times at which to turn on or off the vibrator. The first
     * one is how long to wait before turning it on, and then after that it alternates. If you want to repeat, pass the index into
     * the pattern at which to start the repeat.
     *
     * @param pattern an array of longs of times to turn the vibrator on or off.
     * @param repeat the index into pattern at which to repeat, or -1 if you don't want to repeat.
     */
    public abstract void vibrate(long[] pattern, int repeat);

    /** Stops the vibrator */
    public abstract void cancelVibrate();

    /**
     * The azimuth is the angle of the device's orientation around the z-axis. The positive z-axis points towards the earths
     * center.
     *
     * @return the azimuth in degrees
     * @see <a
     * href="http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])">http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])</a>
     */
    public abstract float getAzimuth();

    /**
     * The pitch is the angle of the device's orientation around the x-axis. The positive x-axis roughly points to the west and is
     * orthogonal to the z- and y-axis.
     *
     * @return the pitch in degrees
     * @see <a
     * href="http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])">http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])</a>
     */
    public abstract float getPitch();

    /**
     * The roll is the angle of the device's orientation around the y-axis. The positive y-axis points to the magnetic north pole
     * of the earth.
     *
     * @return the roll in degrees
     * @see <a
     * href="http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])">http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])</a>
     */
    public abstract float getRoll();

    /**
     * Returns the rotation matrix describing the devices rotation as per <a href=
     * "http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])"
     * >SensorManager#getRotationMatrix(float[], float[], float[], float[])</a>. Does not manipulate the matrix if the platform
     * does not have an accelerometer.
     *
     */
    public abstract void getRotationMatrix(float[] matrix);

    /** @return the time of the event currently reported to the {@link InputProcessor}. */
    public abstract long getCurrentEventTime();

    /**
     * Sets whether the BACK button on Android should be caught. This will prevent the app from being paused. Will have no effect
     * on the desktop.
     *
     * @param catchBack whether to catch the back button
     */
    public abstract void setCatchBackKey(boolean catchBack);

    /** @return whether the back button is currently being caught */
    public abstract boolean isCatchBackKey();

    /**
     * Sets whether the MENU button on Android should be caught. This will prevent the onscreen keyboard to show up. Will have no
     * effect on the desktop.
     *
     * @param catchMenu whether to catch the menu button
     */
    public abstract void setCatchMenuKey(boolean catchMenu);

    /** @return whether the menu button is currently being caught */
    public abstract boolean isCatchMenuKey();

    /**
     * Sets the {@link InputProcessor} that will receive all touch and key input events. It will be called before the
     * {@link ApplicationListener#render()} method each frame.
     *
     * @param processor the InputProcessor
     */
    public void addInputProcessor(InputProcessor processor){
        inputMultiplexer.addProcessor(processor);
    }

    /** @return the currently set {@link InputProcessor} or null. */
    public Array<InputProcessor> getInputProcessors(){
        return inputMultiplexer.getProcessors();
    }

    /**Returns a list of input devices, such as keyboards or controllers.
     * This list always contains a keyboard device, regardless of whether one is connected or not (on Android).*/
    public Array<InputDevice> getDevices(){
        return devices;
    }

    /**Returns the default input device (keyboard).*/
    public KeyboardDevice getKeyboard(){
        return keyboard;
    }

    /**
     * Queries whether a {@link Peripheral} is currently available. In case of Android and the {@link Peripheral#HardwareKeyboard}
     * this returns the whether the keyboard is currently slid out or not.
     *
     * @param peripheral the {@link Peripheral}
     * @return whether the peripheral is available or not.
     */
    public abstract boolean isPeripheralAvailable(Peripheral peripheral);

    /** @return the rotation of the device with respect to its native orientation. */
    public abstract int getRotation();

    /** @return the native orientation of the device. */
    public abstract Orientation getNativeOrientation();

    /**
     * Only viable on the desktop. Will confine the mouse cursor location to the window and hide the mouse cursor. X and y
     * coordinates are still reported as if the mouse was not catched.
     *
     * @param catched whether to catch or not to catch the mouse cursor
     */
    public abstract void setCursorCatched(boolean catched);

    /** @return whether the mouse cursor is catched. */
    public abstract boolean isCursorCatched();

    /**
     * Only viable on the desktop. Will set the mouse cursor location to the given window coordinates (origin top-left corner).
     *
     * @param x the x-position
     * @param y the y-position
     */
    public abstract void setCursorPosition(int x, int y);

    enum Orientation{
        Landscape, Portrait
    }

    /**Enumeration of potentially available peripherals. Use with {@link Input#isPeripheralAvailable(Peripheral)}.*/
    enum Peripheral{
        HardwareKeyboard, OnscreenKeyboard, MultitouchScreen, Accelerometer, Compass, Vibrator, Gyroscope, RotationVector, Pressure
    }
}
