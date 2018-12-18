package com.badlogic.gdx.scene.event;

import com.badlogic.gdx.scene.Element;

public class IbeamCursorListener extends ClickListener{
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Element fromActor){
        super.enter(event, x, y, pointer, fromActor);
        if(pointer == -1 && event.targetActor.isVisible()){
            //TODO implement
            //Cursors.setIbeam();
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Element toActor){
        super.exit(event, x, y, pointer, toActor);
        if(pointer == -1){
            //TODO implement
            //Cursors.restoreCursor();
        }
    }
}
