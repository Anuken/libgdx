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

import com.badlogic.gdx.input.InputProcessor;
import com.badlogic.gdx.input.KeyCode;

/**
 * <p>
 * Interface to the input facilities. This allows polling the state of the keyboard, the touch screen and the accelerometer. On
 * some backends (desktop, gwt, etc) the touch screen is replaced by mouse input. The accelerometer is of course not available on
 * all backends.
 * </p>
 *
 * <p>
 * Instead of polling for events, one can process all input events with an {@link InputProcessor}. You can set the InputProcessor
 * via the {@link #setInputProcessor(InputProcessor)} method. It will be called before the {@link ApplicationListener#render()}
 * method in each frame.
 * </p>
 *
 * <p>
 * The class also offers methods to use (and test for the presence of) other input systems like vibration, compass, on-screen
 * keyboards, and cursor capture. Support for simple input dialogs is also provided.
 * </p>
 *
 * @author mzechner
 */
public interface Input{

    /** @return The acceleration force in m/s^2 applied to the device in the X axis, including the force of gravity */
    float getAccelerometerX();

    /** @return The acceleration force in m/s^2 applied to the device in the Y axis, including the force of gravity */
    float getAccelerometerY();

    /** @return The acceleration force in m/s^2 applied to the device in the Z axis, including the force of gravity */
    float getAccelerometerZ();

    /** @return The rate of rotation in rad/s around the X axis */
    float getGyroscopeX();

    /** @return The rate of rotation in rad/s around the Y axis */
    float getGyroscopeY();

    /** @return The rate of rotation in rad/s around the Z axis */
    float getGyroscopeZ();

    /**
     * @return The x coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first
     * pointer in screen coordinates. The screen origin is the top left corner.
     */
    int mouseX();

    /**
     * Returns the x coordinate in screen coordinates of the given pointer. Pointers are indexed from 0 to n. The pointer id
     * identifies the order in which the fingers went down on the screen, e.g. 0 is the first finger, 1 is the second and so on.
     * When two fingers are touched down and the first one is lifted the second one keeps its index. If another finger is placed on
     * the touch screen the first free index will be used.
     *
     * @param pointer the pointer id.
     * @return the x coordinate
     */
    int mouseX(int pointer);

    /** @return the different between the current pointer location and the last pointer location on the x-axis. */
    int deltaX();

    /** @return the different between the current pointer location and the last pointer location on the x-axis. */
    int deltaX(int pointer);

    /**
     * @return The y coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first
     * pointer in screen coordinates. The screen origin is the bottom left corner.
     */
    int mouseY();

    /**
     * Returns the y coordinate in screen coordinates of the given pointer. Pointers are indexed from 0 to n. The pointer id
     * identifies the order in which the fingers went down on the screen, e.g. 0 is the first finger, 1 is the second and so on.
     * When two fingers are touched down and the first one is lifted the second one keeps its index. If another finger is placed on
     * the touch screen the first free index will be used.
     *
     * @param pointer the pointer id.
     * @return the y coordinate
     */
    int mouseY(int pointer);

    /** @return the different between the current pointer location and the last pointer location on the y-axis. */
    int deltaY();

    /** @return the different between the current pointer location and the last pointer location on the y-axis. */
    int deltaY(int pointer);

    /** @return whether the screen is currently touched. */
    boolean isTouched();

    /** @return whether a new touch down event just occurred. */
    boolean justTouched();

    /**
     * Whether the screen is currently touched by the pointer with the given index. Pointers are indexed from 0 to n. The pointer
     * id identifies the order in which the fingers went down on the screen, e.g. 0 is the first finger, 1 is the second and so on.
     * When two fingers are touched down and the first one is lifted the second one keeps its index. If another finger is placed on
     * the touch screen the first free index will be used.
     *
     * @param pointer the pointer
     * @return whether the screen is touched by the pointer
     */
    boolean isTouched(int pointer);

    /** @return the pressure of the first pointer */
    float getPressure();

    /**
     * Returns the pressure of the given pointer, where 0 is untouched. On Android it should be
     * up to 1.0, but it can go above that slightly and its not consistent between devices. On iOS 1.0 is the normal touch
     * and significantly more of hard touch. Check relevant manufacturer documentation for details.
     * Check availability with {@link Input#isPeripheralAvailable(Peripheral)}. If not supported, returns 1.0 when touched.
     *
     * @param pointer the pointer id.
     * @return the pressure
     */
    float getPressure(int pointer);

    /**Returns whether the key is pressed.*/
    boolean isKeyPressed(KeyCode key);

    /**Returns whether the key has just been pressed.*/
    boolean isKeyTapped(KeyCode key);

    /**Returns whether the key has just been released.*/
    boolean isKeyReleased(KeyCode key);

    /**
     * System dependent method to input a string of text. A dialog box will be created with the given title and the given text as a
     * message for the user. Once the dialog has been closed the provided {@link TextInputListener} will be called on the rendering
     * thread.
     *
     * @param listener The TextInputListener.
     * @param title The title of the text input dialog.
     * @param text The message presented to the user.
     */
    void getTextInput(TextInputListener listener, String title, String text, String hint);

    /**
     * Sets the on-screen keyboard visible if available.
     *
     * @param visible visible or not
     */
    void setOnscreenKeyboardVisible(boolean visible);

    /**
     * Vibrates for the given amount of time. Note that you'll need the permission
     * <code> <uses-permission android:name="android.permission.VIBRATE" /></code> in your manifest file in order for this to work.
     *
     * @param milliseconds the number of milliseconds to vibrate.
     */
    void vibrate(int milliseconds);

    /**
     * Vibrate with a given pattern. Pass in an array of ints that are the times at which to turn on or off the vibrator. The first
     * one is how long to wait before turning it on, and then after that it alternates. If you want to repeat, pass the index into
     * the pattern at which to start the repeat.
     *
     * @param pattern an array of longs of times to turn the vibrator on or off.
     * @param repeat the index into pattern at which to repeat, or -1 if you don't want to repeat.
     */
    void vibrate(long[] pattern, int repeat);

    /** Stops the vibrator */
    void cancelVibrate();

    /**
     * The azimuth is the angle of the device's orientation around the z-axis. The positive z-axis points towards the earths
     * center.
     *
     * @return the azimuth in degrees
     * @see <a
     * href="http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])">http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])</a>
     */
    float getAzimuth();

    /**
     * The pitch is the angle of the device's orientation around the x-axis. The positive x-axis roughly points to the west and is
     * orthogonal to the z- and y-axis.
     *
     * @return the pitch in degrees
     * @see <a
     * href="http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])">http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])</a>
     */
    float getPitch();

    /**
     * The roll is the angle of the device's orientation around the y-axis. The positive y-axis points to the magnetic north pole
     * of the earth.
     *
     * @return the roll in degrees
     * @see <a
     * href="http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])">http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])</a>
     */
    float getRoll();

    /**
     * Returns the rotation matrix describing the devices rotation as per <a href=
     * "http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])"
     * >SensorManager#getRotationMatrix(float[], float[], float[], float[])</a>. Does not manipulate the matrix if the platform
     * does not have an accelerometer.
     *
     * @param matrix
     */
    void getRotationMatrix(float[] matrix);

    /** @return the time of the event currently reported to the {@link InputProcessor}. */
    long getCurrentEventTime();

    /**
     * Sets whether the BACK button on Android should be caught. This will prevent the app from being paused. Will have no effect
     * on the desktop.
     *
     * @param catchBack whether to catch the back button
     */
    void setCatchBackKey(boolean catchBack);

    /** @return whether the back button is currently being caught */
    boolean isCatchBackKey();

    /**
     * Sets whether the MENU button on Android should be caught. This will prevent the onscreen keyboard to show up. Will have no
     * effect on the desktop.
     *
     * @param catchMenu whether to catch the menu button
     */
    void setCatchMenuKey(boolean catchMenu);

    /** @return whether the menu button is currently being caught */
    boolean isCatchMenuKey();

    /**
     * Sets the {@link InputProcessor} that will receive all touch and key input events. It will be called before the
     * {@link ApplicationListener#render()} method each frame.
     *
     * @param processor the InputProcessor
     */
    void setInputProcessor(InputProcessor processor);

    /** @return the currently set {@link InputProcessor} or null. */
    InputProcessor getInputProcessor();

    /**
     * Queries whether a {@link Peripheral} is currently available. In case of Android and the {@link Peripheral#HardwareKeyboard}
     * this returns the whether the keyboard is currently slid out or not.
     *
     * @param peripheral the {@link Peripheral}
     * @return whether the peripheral is available or not.
     */
    boolean isPeripheralAvailable(Peripheral peripheral);

    /** @return the rotation of the device with respect to its native orientation. */
    int getRotation();

    /** @return the native orientation of the device. */
    Orientation getNativeOrientation();

    /**
     * Only viable on the desktop. Will confine the mouse cursor location to the window and hide the mouse cursor. X and y
     * coordinates are still reported as if the mouse was not catched.
     *
     * @param catched whether to catch or not to catch the mouse cursor
     */
    void setCursorCatched(boolean catched);

    /** @return whether the mouse cursor is catched. */
    boolean isCursorCatched();

    /**
     * Only viable on the desktop. Will set the mouse cursor location to the given window coordinates (origin top-left corner).
     *
     * @param x the x-position
     * @param y the y-position
     */
    void setCursorPosition(int x, int y);

    /**Callback interface for {@link Input#getTextInput(TextInputListener, String, String, String)}*/
    interface TextInputListener{
        void input(String text);

        void canceled();
    }

    enum Orientation{
        Landscape, Portrait
    }

    /**Enumeration of potentially available peripherals. Use with {@link Input#isPeripheralAvailable(Peripheral)}.*/
    enum Peripheral{
        HardwareKeyboard, OnscreenKeyboard, MultitouchScreen, Accelerometer, Compass, Vibrator, Gyroscope, RotationVector, Pressure
    }
}
