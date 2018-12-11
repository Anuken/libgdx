package com.badlogic.gdx.scene.utils;

import com.badlogic.gdx.Core;
import com.badlogic.gdx.utils.OS;

public class UIUtils{

    static public boolean isDisabled(Element element){
        return element != null && ((element instanceof Disableable && ((Disableable) element).isDisabled()) || !element.isVisible());
    }

    static public boolean portrait(){
        return Core.graphics.getHeight() > Core.graphics.getWidth();
    }

    static public boolean left(){
        return Core.input.isButtonPressed(Buttons.LEFT);
    }

    static public boolean left(int button){
        return button == Buttons.LEFT;
    }

    static public boolean right(){
        return Core.input.isButtonPressed(Buttons.RIGHT);
    }

    static public boolean right(int button){
        return button == Buttons.RIGHT;
    }

    static public boolean middle(){
        return Core.input.isButtonPressed(Buttons.MIDDLE);
    }

    static public boolean middle(int button){
        return button == Buttons.MIDDLE;
    }

    static public boolean shift(){
        return Core.input.isKeyPressed(Keys.SHIFT_LEFT) || Core.input.isKeyPressed(Keys.SHIFT_RIGHT);
    }

    static public boolean shift(int keycode){
        return keycode == Keys.SHIFT_LEFT || keycode == Keys.SHIFT_RIGHT;
    }

    static public boolean ctrl(){
        if(OS.isMac)
            return Core.input.isKeyPressed(Keys.SYM);
        else
            return Core.input.isKeyPressed(Keys.CONTROL_LEFT) || Core.input.isKeyPressed(Keys.CONTROL_RIGHT);
    }

    static public boolean ctrl(int keycode){
        if(OS.isMac)
            return keycode == Keys.SYM;
        else
            return keycode == Keys.CONTROL_LEFT || keycode == Keys.CONTROL_RIGHT;
    }

    static public boolean alt(){
        return Core.input.isKeyPressed(Keys.ALT_LEFT) || Core.input.isKeyPressed(Keys.ALT_RIGHT);
    }

    static public boolean alt(int keycode){
        return keycode == Keys.ALT_LEFT || keycode == Keys.ALT_RIGHT;
    }
}
