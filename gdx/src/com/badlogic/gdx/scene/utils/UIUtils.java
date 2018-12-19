package com.badlogic.gdx.scene.utils;

import com.badlogic.gdx.Core;
import com.badlogic.gdx.input.KeyCode;
import com.badlogic.gdx.scene.Element;
import com.badlogic.gdx.utils.OS;

public class UIUtils{

    static public boolean isDisabled(Element element){
        return element != null && ((element instanceof Disableable && ((Disableable)element).isDisabled()) || !element.isVisible());
    }

    static public boolean portrait(){
        return Core.graphics.getHeight() > Core.graphics.getWidth();
    }

    static public boolean left(){
        return Core.input.isKeyPressed(KeyCode.MOUSE_LEFT);
    }

    static public boolean left(KeyCode button){
        return button == KeyCode.MOUSE_LEFT;
    }

    static public boolean right(){
        return Core.input.isKeyPressed(KeyCode.MOUSE_RIGHT);
    }

    static public boolean right(KeyCode button){
        return button == KeyCode.MOUSE_RIGHT;
    }

    static public boolean middle(){
        return Core.input.isKeyPressed(KeyCode.MOUSE_MIDDLE);
    }

    static public boolean middle(KeyCode button){
        return button == KeyCode.MOUSE_MIDDLE;
    }

    static public boolean shift(){
        return Core.input.isKeyPressed(KeyCode.SHIFT_LEFT) || Core.input.isKeyPressed(KeyCode.SHIFT_RIGHT);
    }

    static public boolean shift(KeyCode keycode){
        return keycode == KeyCode.SHIFT_LEFT || keycode == KeyCode.SHIFT_RIGHT;
    }

    static public boolean ctrl(){
        if(OS.isMac)
            return Core.input.isKeyPressed(KeyCode.SYM);
        else
            return Core.input.isKeyPressed(KeyCode.CONTROL_LEFT) || Core.input.isKeyPressed(KeyCode.CONTROL_RIGHT);
    }

    static public boolean ctrl(KeyCode keycode){
        if(OS.isMac)
            return keycode == KeyCode.SYM;
        else
            return keycode == KeyCode.CONTROL_LEFT || keycode == KeyCode.CONTROL_RIGHT;
    }

    static public boolean alt(){
        return Core.input.isKeyPressed(KeyCode.ALT_LEFT) || Core.input.isKeyPressed(KeyCode.ALT_RIGHT);
    }

    static public boolean alt(KeyCode keycode){
        return keycode == KeyCode.ALT_LEFT || keycode == KeyCode.ALT_RIGHT;
    }
}
