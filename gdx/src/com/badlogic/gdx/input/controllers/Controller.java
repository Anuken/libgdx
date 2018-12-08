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

package com.badlogic.gdx.input.controllers;

import com.badlogic.gdx.math.geom.Vector3;

/**
 * Represents a connected controller. Provides methods to query the state of buttons, axes, POVs, sliders and accelerometers on
 * the controller. Multiple {@link ControllerListener} instances can be registered with the Controller to receive events in case
 * the controller's state changes. Listeners will be invoked on the rendering thread.
 *
 * @author Nathan Sweet
 */
public interface Controller{
    /**
     * @return whether the button is pressed.
     */
    boolean getButton(int buttonCode);

    /**
     * @return the value of the axis, between -1 and 1
     */
    float getAxis(int axisCode);

    /**
     * @return the {@link PovDirection}
     */
    PovDirection getPov(int povCode);

    /**
     * @return whether the slider is pressed
     */
    boolean getSliderX(int sliderCode);

    /**
     * @return whether the slider is pressed
     */
    boolean getSliderY(int sliderCode);

    /**
     * @return the accelerometer values on the 3 axis, in m/s^2
     */
    Vector3 getAccelerometer(int accelerometerCode);

    /** @param sensitivity the accelerometer sensitive, 0 (lowest) to 1 (highest) */
    void setAccelerometerSensitivity(float sensitivity);

    /** @return the device name */
    String getName();

    /**
     * Adds a new {@link ControllerListener} to this {@link Controller}. The listener will receive calls in case the state of the
     * controller changes. The listener will be invoked on the rendering thread.
     *
     */
    void addListener(ControllerListener listener);

    /**
     * Removes the given {@link ControllerListener}
     *
     */
    void removeListener(ControllerListener listener);
}
