package com.badlogic.gdx.scene.style;

/** A drawable that supports scale and rotation. */
public interface TransformDrawable extends Drawable{
    void draw(Batch batch, float x, float y, float originX, float originY, float width, float height, float scaleX,
              float scaleY, float rotation);
}
