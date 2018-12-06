package com.badlogic.gdx.math.geom;

import com.badlogic.gdx.math.Mathf;

/**Represents a point in 2-D space.*/
public interface Position{
    float getX();
    float getY();

    default float angleTo(Position other){
        return Mathf.atan2(other.getX() - getX(), other.getY() - getY());
    }

    default float dst(Position other){
        final float xd = getX() - other.getX();
        final float yd = getX() - other.getY();
        return (float) Math.sqrt(xd * xd + yd * yd);
    }
}
