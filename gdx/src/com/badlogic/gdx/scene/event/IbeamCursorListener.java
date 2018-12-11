package com.badlogic.gdx.scene.event;

import com.badlogic.gdx.scene.Element;
import com.badlogic.gdx.scene.utils.Cursors;

public class IbeamCursorListener extends ClickListener{
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Element fromActor){
        super.enter(event, x, y, pointer, fromActor);
        if(pointer == -1 && event.targetActor.isVisible()){
            Cursors.setIbeam();
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Element toActor){
        super.exit(event, x, y, pointer, toActor);
        if(pointer == -1){
            Cursors.restoreCursor();
        }
    }
}
