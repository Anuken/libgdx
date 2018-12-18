package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.g2d.SpriteBatch.BatchRect;
import com.badlogic.gdx.math.Mathf;

import static com.badlogic.gdx.Core.atlas;
import static com.badlogic.gdx.Core.graphics;

public class Fill{
    private static float[] vertices = new float[20];
    private static TextureRegion circleRegion;

    public static void quad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4){
        float color = graphics.batch().getColor().toFloatBits();
        float u = Draw.getBlankRegion().getU();
        float v = Draw.getBlankRegion().getV();
        vertices[0] = x1;
        vertices[1] = y1;
        vertices[2] = color;
        vertices[3] = u;
        vertices[4] = v;

        vertices[5] = x2;
        vertices[6] = y2;
        vertices[7] = color;
        vertices[8] = u;
        vertices[9] = v;

        vertices[10] = x3;
        vertices[11] = y3;
        vertices[12] = color;
        vertices[13] = u;
        vertices[14] = v;

        vertices[15] = x4;
        vertices[16] = y4;
        vertices[17] = color;
        vertices[18] = u;
        vertices[19] = v;

        graphics.batch().draw().vert(Draw.getBlankRegion().getTexture(), vertices, 0, vertices.length);
    }

    public static void tri(float x1, float y1, float x2, float y2, float x3, float y3){
        quad(x1, y1, x2, y2, x3, y3, x3, y3);
    }

    public static void poly(float x, float y, int sides, float radius){
        poly(x, y, sides, radius, 0f);
    }

    public static void poly(float x, float y, int sides, float radius, float rotation){
        float space = 360f/sides;

        for(int i = 0; i < sides-2; i += 3){
            float px = Mathf.trnsx(space * i+rotation, radius);
            float py = Mathf.trnsy(space * i+rotation, radius);
            float px2 = Mathf.trnsx(space * (i+1)+rotation, radius);
            float py2 = Mathf.trnsy(space * (i+1)+rotation, radius);
            float px3 = Mathf.trnsx(space * (i+2)+rotation, radius);
            float py3 = Mathf.trnsy(space * (i+2)+rotation, radius);
            float px4 = Mathf.trnsx(space * (i+3)+rotation, radius);
            float py4 = Mathf.trnsy(space * (i+3)+rotation, radius);
            quad(x+px, y+py, x+px2, y+py2, x+px3, y+py3, x+px4, y + py4);
        }

        int mod = sides % 3;

        for(int i = sides - mod - 1; i < sides; i++){
            float px = Mathf.trnsx(space * i+rotation, radius);
            float py = Mathf.trnsy(space * i+rotation, radius);
            float px2 = Mathf.trnsx(space * (i+1)+rotation, radius);
            float py2 = Mathf.trnsy(space * (i+1)+rotation, radius);
            tri(x, y, x + px, y + py, x + px2, y + py2);
        }
    }

    public static void circle(float x, float y, float radius){
        if(circleRegion == null){
            circleRegion = atlas.find("circle");
        }

        graphics.batch().draw().tex(circleRegion).set(x, y, radius * 2, radius * 2);
    }

    public static BatchRect rect(){
        return graphics.batch().draw().tex(Draw.getBlankRegion());
    }
}
