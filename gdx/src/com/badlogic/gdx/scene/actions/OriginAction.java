package com.badlogic.gdx.scene.actions;

import com.badlogic.gdx.scene.Action;
import com.badlogic.gdx.utils.Align;

public class OriginAction extends Action{

    @Override
    public boolean act(float delta){
        actor.setOrigin(Align.center);
        return true;
    }

}
