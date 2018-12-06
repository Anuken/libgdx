package com.badlogic.gdx.scene.event;

import io.anuke.ucore.function.BooleanProvider;
import com.badlogic.gdx.scene.Element;
import com.badlogic.gdx.scene.utils.Cursors;
import com.badlogic.gdx.scene.utils.UIUtils;

public class HandCursorListener extends ClickListener{
    private BooleanProvider enabled = () -> true;
    private boolean set;

    public void setEnabled(BooleanProvider vis){
        this.enabled = vis;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Element fromActor){
        super.enter(event, x, y, pointer, fromActor);

        if(!enabled.get() || UIUtils.isDisabled(event.getTarget()) || UIUtils.isDisabled(fromActor) || pointer != -1){
            return;
        }

        Cursors.setHand();
        set = true;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Element toActor){
        super.exit(event, x, y, pointer, toActor);

        if(!enabled.get() || !set) return;

        if(pointer == -1){
            Cursors.restoreCursor();
        }
        set = false;
    }
}
