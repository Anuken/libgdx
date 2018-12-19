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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.view.*;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Core;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.InputDevice;
import com.badlogic.gdx.input.InputProcessor;
import com.badlogic.gdx.input.KeyCode;
import com.badlogic.gdx.math.geom.Vector3;
import com.badlogic.gdx.utils.Bits;
import com.badlogic.gdx.utils.Log;
import com.badlogic.gdx.utils.pooling.Pool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of the {@link Input} interface for Android.
 *
 * @author mzechner
 */

/** @author jshapcot */
public class AndroidInput extends Input implements OnKeyListener, OnTouchListener{
    public static final int NUM_TOUCHES = 20;
    ArrayList<OnKeyListener> keyListeners = new ArrayList<>();
    ArrayList<KeyEvent> keyEvents = new ArrayList<>();
    ArrayList<TouchEvent> touchEvents = new ArrayList<>();
    int[] touchX = new int[NUM_TOUCHES];
    int[] touchY = new int[NUM_TOUCHES];
    int[] deltaX = new int[NUM_TOUCHES];
    int[] deltaY = new int[NUM_TOUCHES];
    boolean[] touched = new boolean[NUM_TOUCHES];
    int[] button = new int[NUM_TOUCHES];
    int[] realId = new int[NUM_TOUCHES];
    float[] pressure = new float[NUM_TOUCHES];
    final boolean hasMultitouch;
    private Bits keys = new Bits(KeyCode.values().length);
    private Bits justPressedKeys = new Bits(KeyCode.values().length);
    private SensorManager manager;
    public boolean accelerometerAvailable = false;
    protected final float[] accelerometerValues = new float[3];
    public boolean gyroscopeAvailable = false;
    protected final float[] gyroscopeValues = new float[3];
    private String text = null;
    private Handler handle;
    final Application app;
    final Context context;
    protected final AndroidTouchHandler touchHandler;
    private int sleepTime;
    protected final Vibrator vibrator;
    private boolean compassAvailable = false;
    private boolean rotationVectorAvailable = false;
    boolean keyboardAvailable;
    protected final float[] magneticFieldValues = new float[3];
    protected final float[] rotationVectorValues = new float[3];
    private float azimuth = 0;
    private float pitch = 0;
    private float roll = 0;
    private boolean justTouched = false;
    private InputProcessor processor;
    private final AndroidApplicationConfiguration config;
    protected final Orientation nativeOrientation;
    private long currentEventTimeStamp = System.nanoTime();
    private final AndroidOnscreenKeyboard onscreenKeyboard;

    private Vector3 accel = new Vector3(), gyro = new Vector3(), orient = new Vector3();

    private SensorEventListener accelerometerListener;
    private SensorEventListener gyroscopeListener;
    private SensorEventListener compassListener;
    private SensorEventListener rotationVectorListener;

    static class KeyEvent{
        static final int KEY_DOWN = 0;
        static final int KEY_UP = 1;
        static final int KEY_TYPED = 2;

        long timeStamp;
        int type;
        KeyCode keyCode;
        char keyChar;
    }

    static class TouchEvent{
        static final int TOUCH_DOWN = 0;
        static final int TOUCH_UP = 1;
        static final int TOUCH_DRAGGED = 2;
        static final int TOUCH_SCROLLED = 3;
        static final int TOUCH_MOVED = 4;

        long timeStamp;
        int type;
        int x;
        int y;
        int scrollAmountX;
        int scrollAmountY;
        KeyCode button;
        int pointer;
    }

    Pool<KeyEvent> usedKeyEvents = new Pool<KeyEvent>(16, 1000){
        protected KeyEvent newObject(){
            return new KeyEvent();
        }
    };

    Pool<TouchEvent> usedTouchEvents = new Pool<TouchEvent>(16, 1000){
        protected TouchEvent newObject(){
            return new TouchEvent();
        }
    };

    public AndroidInput(Application activity, Context context, Object view, AndroidApplicationConfiguration config){
        // we hook into View, for LWPs we call onTouch below directly from
        // within the AndroidLivewallpaperEngine#onTouchEvent() method.
        if(view instanceof View){
            View v = (View) view;
            v.setOnKeyListener(this);
            v.setOnTouchListener(this);
            v.setFocusable(true);
            v.setFocusableInTouchMode(true);
            v.requestFocus();
        }
        this.config = config;
        this.onscreenKeyboard = new AndroidOnscreenKeyboard(context, new Handler(), this);

        for(int i = 0; i < realId.length; i++)
            realId[i] = -1;
        handle = new Handler();
        this.app = activity;
        this.context = context;
        this.sleepTime = config.touchSleepTime;
        touchHandler = new AndroidMultiTouchHandler();
        hasMultitouch = touchHandler.supportsMultitouch(context);

        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        int rotation = getRotation();
        DisplayMode mode = Core.graphics.getDisplayMode();
        if(((rotation == 0 || rotation == 180) && (mode.width >= mode.height))
        || ((rotation == 90 || rotation == 270) && (mode.width <= mode.height))){
            nativeOrientation = Orientation.Landscape;
        }else{
            nativeOrientation = Orientation.Portrait;
        }
    }

    @Override
    public Vector3 getAccelerometer(){
        return accel.set(accelerometerValues);
    }

    @Override
    public Vector3 getGyroscope(){
        return gyro.set(gyroscopeValues);
    }

    @Override
    public Vector3 getOrientation(){
        if(!compassAvailable && !rotationVectorAvailable) return Vector3.Zero;

        updateOrientation();

        return orient.set(pitch, roll, azimuth);
    }

    @Override
    public void getTextInput(TextInput info){
        handle.post(() -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            //alert.setTitle(info.title);
            final EditText input = new EditText(context);
            input.setText(text);
            if(!info.multiline) input.setSingleLine();
            input.setSelection(text.length());
            alert.setView(input);
            alert.setPositiveButton(context.getString(android.R.string.ok), new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int whichButton){
                    Core.app.post(() -> info.accepted.accept(input.getText().toString()));
                }
            });
            alert.setNegativeButton(context.getString(android.R.string.cancel), new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int whichButton){
                    Core.app.post(() -> info.canceled.run());
                }
            });
            alert.setOnCancelListener(new OnCancelListener(){
                @Override
                public void onCancel(DialogInterface arg0){
                    Core.app.post(() -> info.canceled.run());
                }
            });
            AlertDialog dialog = alert.show();
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        });
    }

    @Override
    public int mouseX(){
        synchronized(this){
            return touchX[0];
        }
    }

    @Override
    public int mouseY(){
        synchronized(this){
            return touchY[0];
        }
    }

    @Override
    public int mouseX(int pointer){
        synchronized(this){
            return touchX[pointer];
        }
    }

    @Override
    public int mouseY(int pointer){
        synchronized(this){
            return touchY[pointer];
        }
    }

    public boolean isTouched(int pointer){
        synchronized(this){
            return touched[pointer];
        }
    }

    @Override
    public float getPressure(){
        return getPressure(0);
    }

    @Override
    public float getPressure(int pointer){
        return pressure[pointer];
    }

    @Override
    public boolean isTouched(){
        synchronized(this){
            if(hasMultitouch){
                for(int pointer = 0; pointer < NUM_TOUCHES; pointer++){
                    if(touched[pointer]){
                        return true;
                    }
                }
            }
            return touched[0];
        }
    }

    void processDevices(){
        for(InputDevice device : devices){
            device.update();
        }
    }

    void processEvents(){
        synchronized(this){
            justTouched = false;

            if(processor != null){
                final InputProcessor processor = this.processor;

                int len = keyEvents.size();
                for(int i = 0; i < len; i++){
                    KeyEvent e = keyEvents.get(i);
                    currentEventTimeStamp = e.timeStamp;
                    switch(e.type){
                        case KeyEvent.KEY_DOWN:
                            processor.keyDown(e.keyCode);
                            break;
                        case KeyEvent.KEY_UP:
                            processor.keyUp(e.keyCode);
                            break;
                        case KeyEvent.KEY_TYPED:
                            processor.keyTyped(e.keyChar);
                    }
                    usedKeyEvents.free(e);
                }

                len = touchEvents.size();
                for(int i = 0; i < len; i++){
                    TouchEvent e = touchEvents.get(i);
                    currentEventTimeStamp = e.timeStamp;
                    switch(e.type){
                        case TouchEvent.TOUCH_DOWN:
                            processor.touchDown(e.x, e.y, e.pointer, e.button);
                            justTouched = true;
                            break;
                        case TouchEvent.TOUCH_UP:
                            processor.touchUp(e.x, e.y, e.pointer, e.button);
                            break;
                        case TouchEvent.TOUCH_DRAGGED:
                            processor.touchDragged(e.x, e.y, e.pointer);
                            break;
                        case TouchEvent.TOUCH_MOVED:
                            processor.mouseMoved(e.x, e.y);
                            break;
                        case TouchEvent.TOUCH_SCROLLED:
                            processor.scrolled(e.scrollAmountX, e.scrollAmountY);
                    }
                    usedTouchEvents.free(e);
                }
            }else{
                int len = touchEvents.size();
                for(int i = 0; i < len; i++){
                    TouchEvent e = touchEvents.get(i);
                    if(e.type == TouchEvent.TOUCH_DOWN) justTouched = true;
                    usedTouchEvents.free(e);
                }

                len = keyEvents.size();
                for(int i = 0; i < len; i++){
                    usedKeyEvents.free(keyEvents.get(i));
                }
            }

            if(touchEvents.isEmpty()){
                for(int i = 0; i < deltaX.length; i++){
                    deltaX[0] = 0;
                    deltaY[0] = 0;
                }
            }

            keyEvents.clear();
            touchEvents.clear();
        }
    }

    boolean requestFocus = true;

    @Override
    public boolean onTouch(View view, MotionEvent event){
        if(requestFocus && view != null){
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            requestFocus = false;
        }

        // synchronized in handler.postTouchEvent()
        touchHandler.onTouch(event, this);

        if(sleepTime != 0){
            try{
                Thread.sleep(sleepTime);
            }catch(InterruptedException e){
            }
        }
        return true;
    }

    /** Called in {@link AndroidLiveWallpaperService} on tap
     * @param x
     * @param y */
    public void onTap(int x, int y){
        postTap(x, y);
    }

    /** Called in {@link AndroidLiveWallpaperService} on drop
     * @param x
     * @param y */
    public void onDrop(int x, int y){
        postTap(x, y);
    }

    protected void postTap(int x, int y){
        synchronized(this){
            TouchEvent event = usedTouchEvents.obtain();
            event.timeStamp = System.nanoTime();
            event.pointer = 0;
            event.x = x;
            event.y = y;
            event.type = TouchEvent.TOUCH_DOWN;
            touchEvents.add(event);

            event = usedTouchEvents.obtain();
            event.timeStamp = System.nanoTime();
            event.pointer = 0;
            event.x = x;
            event.y = y;
            event.type = TouchEvent.TOUCH_UP;
            touchEvents.add(event);
        }
        Core.graphics.requestRendering();
    }

    @Override
    public boolean onKey(View v, int keyCode, android.view.KeyEvent e){
        for(int i = 0, n = keyListeners.size(); i < n; i++)
            if(keyListeners.get(i).onKey(v, keyCode, e)) return true;

        synchronized(this){
            KeyEvent event;

            if(e.getKeyCode() == android.view.KeyEvent.KEYCODE_UNKNOWN && e.getAction() == android.view.KeyEvent.ACTION_MULTIPLE){
                String chars = e.getCharacters();
                for(int i = 0; i < chars.length(); i++){
                    event = usedKeyEvents.obtain();
                    event.timeStamp = System.nanoTime();
                    event.keyCode = KeyCode.UNKNOWN;
                    event.keyChar = chars.charAt(i);
                    event.type = KeyEvent.KEY_TYPED;
                    keyEvents.add(event);
                }
                return false;
            }

            char character = (char) e.getUnicodeChar();
            // Android doesn't report a unicode char for back space. hrm...
            if(keyCode == 67) character = '\b';
            if(e.getKeyCode() < 0){
                return false;
            }

            KeyCode code = AndroidInputMap.getKeyCode(e.getKeyCode());

            switch(e.getAction()){
                case android.view.KeyEvent.ACTION_DOWN:
                    event = usedKeyEvents.obtain();
                    event.timeStamp = System.nanoTime();
                    event.keyChar = 0;
                    event.keyCode = code;
                    event.type = KeyEvent.KEY_DOWN;

                    // Xperia hack for circle key. gah...
                    if(keyCode == android.view.KeyEvent.KEYCODE_BACK && e.isAltPressed()){
                        keyCode = 255;
                        event.keyCode = KeyCode.BUTTON_CIRCLE;
                    }

                    keyEvents.add(event);
                    if(!keys.get(e.getKeyCode())){
                        keys.set(e.getKeyCode());
                        justPressedKeys.set(e.getKeyCode());
                    }else{
                        justPressedKeys.clear(e.getKeyCode());
                    }
                    break;
                case android.view.KeyEvent.ACTION_UP:
                    long timeStamp = System.nanoTime();
                    event = usedKeyEvents.obtain();
                    event.timeStamp = timeStamp;
                    event.keyChar = 0;
                    event.keyCode = code;
                    event.type = KeyEvent.KEY_UP;
                    // Xperia hack for circle key. gah...
                    if(keyCode == android.view.KeyEvent.KEYCODE_BACK && e.isAltPressed()){
                        keyCode = 255;
                        event.keyCode = KeyCode.BUTTON_CIRCLE;
                    }
                    keyEvents.add(event);

                    event = usedKeyEvents.obtain();
                    event.timeStamp = timeStamp;
                    event.keyChar = character;
                    event.keyCode = KeyCode.UNKNOWN;
                    event.type = KeyEvent.KEY_TYPED;
                    keyEvents.add(event);

                    if(keyCode == 255){
                        keys.clear(255);
                    }else{
                        if(keys.get(e.getKeyCode())){
                            keys.clear(e.getKeyCode());
                            justPressedKeys.clear(e.getKeyCode());
                        }
                    }
            }
            Core.graphics.requestRendering();
        }

        // circle button on Xperia Play shouldn't need catchBack == true
        if(keyCode == 255) return true;
        return caughtKeys.contains(AndroidInputMap.getKeyCode(keyCode).ordinal());
    }

    @Override
    public void setOnscreenKeyboardVisible(final boolean visible){
        handle.post(new Runnable(){
            public void run(){
                InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if(visible){
                    View view = ((AndroidGraphics) Core.graphics).getView();
                    view.setFocusable(true);
                    view.setFocusableInTouchMode(true);
                    manager.showSoftInput(((AndroidGraphics) Core.graphics).getView(), 0);
                }else{
                    manager.hideSoftInputFromWindow(((AndroidGraphics) Core.graphics).getView().getWindowToken(), 0);
                }
            }
        });
    }

    @Override
    public void vibrate(int milliseconds){
        vibrator.vibrate(milliseconds);
    }

    @Override
    public void vibrate(long[] pattern, int repeat){
        vibrator.vibrate(pattern, repeat);
    }

    @Override
    public void cancelVibrate(){
        vibrator.cancel();
    }

    @Override
    public boolean justTouched(){
        return justTouched;
    }

    final float[] R = new float[9];
    final float[] orientation = new float[3];

    private void updateOrientation(){
        if(rotationVectorAvailable){
            SensorManager.getRotationMatrixFromVector(R, rotationVectorValues);
        }else if(!SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues)){
            return; // compass + accelerometer in free fall
        }
        SensorManager.getOrientation(R, orientation);
        azimuth = (float) Math.toDegrees(orientation[0]);
        pitch = (float) Math.toDegrees(orientation[1]);
        roll = (float) Math.toDegrees(orientation[2]);
    }

    /** Returns the rotation matrix describing the devices rotation as per <a href=
     * "http://developer.android.com/reference/android/hardware/SensorManager.html#getRotationMatrix(float[], float[], float[], float[])"
     * >SensorManager#getRotationMatrix(float[], float[], float[], float[])</a>. Does not manipulate the matrix if the platform
     * does not have an accelerometer and compass, or a rotation vector sensor.
     * @param matrix */
    @Override
    public void getRotationMatrix(float[] matrix){
        if(rotationVectorAvailable)
            SensorManager.getRotationMatrixFromVector(matrix, rotationVectorValues);
        else // compass + accelerometer
            SensorManager.getRotationMatrix(matrix, null, accelerometerValues, magneticFieldValues);
    }

    void registerSensorListeners(){
        if(config.useAccelerometer){
            manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).isEmpty()){
                accelerometerAvailable = false;
            }else{
                Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
                accelerometerListener = new SensorListener();
                accelerometerAvailable = manager.registerListener(accelerometerListener, accelerometer,
                config.sensorDelay);
            }
        }else
            accelerometerAvailable = false;

        if(config.useGyroscope){
            manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            if(manager.getSensorList(Sensor.TYPE_GYROSCOPE).isEmpty()){
                gyroscopeAvailable = false;
            }else{
                Sensor gyroscope = manager.getSensorList(Sensor.TYPE_GYROSCOPE).get(0);
                gyroscopeListener = new SensorListener();
                gyroscopeAvailable = manager.registerListener(gyroscopeListener, gyroscope,
                config.sensorDelay);
            }
        }else
            gyroscopeAvailable = false;

        rotationVectorAvailable = false;
        if(config.useRotationVectorSensor){
            if(manager == null) manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            List<Sensor> rotationVectorSensors = manager.getSensorList(Sensor.TYPE_ROTATION_VECTOR);
            if(!rotationVectorSensors.isEmpty()){
                rotationVectorListener = new SensorListener();
                for(Sensor sensor : rotationVectorSensors){ // favor AOSP sensor
                    if(sensor.getVendor().equals("Google Inc.") && sensor.getVersion() == 3){
                        rotationVectorAvailable = manager.registerListener(rotationVectorListener, sensor,
                        config.sensorDelay);
                        break;
                    }
                }
                if(!rotationVectorAvailable)
                    rotationVectorAvailable = manager.registerListener(rotationVectorListener, rotationVectorSensors.get(0),
                    config.sensorDelay);
            }
        }

        if(config.useCompass && !rotationVectorAvailable){
            if(manager == null) manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            if(sensor != null){
                compassAvailable = accelerometerAvailable;
                if(compassAvailable){
                    compassListener = new SensorListener();
                    compassAvailable = manager.registerListener(compassListener, sensor, config.sensorDelay);
                }
            }else{
                compassAvailable = false;
            }
        }else
            compassAvailable = false;
        Log.infoTag("AndroidInput", "sensor listener setup");
    }

    void unregisterSensorListeners(){
        if(manager != null){
            if(accelerometerListener != null){
                manager.unregisterListener(accelerometerListener);
                accelerometerListener = null;
            }
            if(gyroscopeListener != null){
                manager.unregisterListener(gyroscopeListener);
                gyroscopeListener = null;
            }
            if(rotationVectorListener != null){
                manager.unregisterListener(rotationVectorListener);
                rotationVectorListener = null;
            }
            if(compassListener != null){
                manager.unregisterListener(compassListener);
                compassListener = null;
            }
            manager = null;
        }
        Log.infoTag("AndroidInput", "sensor listener tear down");
    }

    @Override
    public boolean isPeripheralAvailable(Peripheral peripheral){
        if(peripheral == Peripheral.Accelerometer) return accelerometerAvailable;
        if(peripheral == Peripheral.Gyroscope) return gyroscopeAvailable;
        if(peripheral == Peripheral.Compass) return compassAvailable;
        if(peripheral == Peripheral.HardwareKeyboard) return keyboardAvailable;
        if(peripheral == Peripheral.OnscreenKeyboard) return true;
        if(peripheral == Peripheral.Vibrator)
            return (Build.VERSION.SDK_INT >= 11 && vibrator != null) ? vibrator.hasVibrator() : vibrator != null;
        if(peripheral == Peripheral.MultitouchScreen) return hasMultitouch;
        if(peripheral == Peripheral.RotationVector) return rotationVectorAvailable;
        return peripheral == Peripheral.Pressure;
    }

    public int getFreePointerIndex(){
        int len = realId.length;
        for(int i = 0; i < len; i++){
            if(realId[i] == -1) return i;
        }

        realId = resize(realId);
        touchX = resize(touchX);
        touchY = resize(touchY);
        deltaX = resize(deltaX);
        deltaY = resize(deltaY);
        touched = resize(touched);
        button = resize(button);

        return len;
    }

    private int[] resize(int[] orig){
        int[] tmp = new int[orig.length + 2];
        System.arraycopy(orig, 0, tmp, 0, orig.length);
        return tmp;
    }

    private boolean[] resize(boolean[] orig){
        boolean[] tmp = new boolean[orig.length + 2];
        System.arraycopy(orig, 0, tmp, 0, orig.length);
        return tmp;
    }

    public int lookUpPointerIndex(int pointerId){
        int len = realId.length;
        for(int i = 0; i < len; i++){
            if(realId[i] == pointerId) return i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            sb.append(i + ":" + realId[i] + " ");
        }
        Log.infoTag("AndroidInput", "Pointer ID lookup failed: " + pointerId + ", " + sb.toString());
        return -1;
    }

    @Override
    public int getRotation(){
        int orientation = 0;

        if(context instanceof Activity){
            orientation = ((Activity) context).getWindowManager().getDefaultDisplay().getRotation();
        }else{
            orientation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        }

        switch(orientation){
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    @Override
    public Orientation getNativeOrientation(){
        return nativeOrientation;
    }

    @Override
    public void setCursorCatched(boolean catched){
    }

    @Override
    public boolean isCursorCatched(){
        return false;
    }

    @Override
    public int deltaX(){
        return deltaX[0];
    }

    @Override
    public int deltaX(int pointer){
        return deltaX[pointer];
    }

    @Override
    public int deltaY(){
        return deltaY[0];
    }

    @Override
    public int deltaY(int pointer){
        return deltaY[pointer];
    }

    @Override
    public void setCursorPosition(int x, int y){
    }

    @Override
    public long getCurrentEventTime(){
        return currentEventTimeStamp;
    }

    public void addKeyListener(OnKeyListener listener){
        keyListeners.add(listener);
    }

    public void onPause(){
        unregisterSensorListeners();

        // erase pointer ids. this sucks donkeyballs...
        Arrays.fill(realId, -1);

        // erase touched state. this also sucks donkeyballs...
        Arrays.fill(touched, false);
    }

    public void onResume(){
        registerSensorListeners();
    }

    /** Our implementation of SensorEventListener. Because Android doesn't like it when we register more than one Sensor to a single
     * SensorEventListener, we add one of these for each Sensor. Could use an anonymous class, but I don't see any harm in
     * explicitly defining it here. Correct me if I am wrong. */
    private class SensorListener implements SensorEventListener{

        public SensorListener(){

        }

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1){

        }

        @Override
        public void onSensorChanged(SensorEvent event){
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                if(nativeOrientation == Orientation.Portrait){
                    System.arraycopy(event.values, 0, accelerometerValues, 0, accelerometerValues.length);
                }else{
                    accelerometerValues[0] = event.values[1];
                    accelerometerValues[1] = -event.values[0];
                    accelerometerValues[2] = event.values[2];
                }
            }
            if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                System.arraycopy(event.values, 0, magneticFieldValues, 0, magneticFieldValues.length);
            }
            if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
                if(nativeOrientation == Orientation.Portrait){
                    System.arraycopy(event.values, 0, gyroscopeValues, 0, gyroscopeValues.length);
                }else{
                    gyroscopeValues[0] = event.values[1];
                    gyroscopeValues[1] = -event.values[0];
                    gyroscopeValues[2] = event.values[2];
                }
            }
            if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
                if(nativeOrientation == Orientation.Portrait){
                    System.arraycopy(event.values, 0, rotationVectorValues, 0, rotationVectorValues.length);
                }else{
                    rotationVectorValues[0] = event.values[1];
                    rotationVectorValues[1] = -event.values[0];
                    rotationVectorValues[2] = event.values[2];
                }
            }
        }
    }
}
