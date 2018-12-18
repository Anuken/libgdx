package com.badlogic.gdx.scene.ui;

import com.badlogic.gdx.graphics.Color;

public class ColorImage extends Image{
    private Color set;

    public ColorImage(Color set){
        super("white");
        this.set = set;
    }

    @Override
    public void draw(){
        setColor(set);
        super.draw();
    }
}
