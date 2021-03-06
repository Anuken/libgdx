package io.anuke.arc.scene.style;

/** A drawable that supports scale and rotation. */
public interface TransformDrawable extends Drawable{
    void draw(float x, float y, float originX, float originY, float width, float height, float scaleX,
              float scaleY, float rotation);
}
