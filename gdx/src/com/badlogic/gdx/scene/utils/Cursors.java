package com.badlogic.gdx.scene.utils;

import com.badlogic.gdx.Core;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import io.anuke.ucore.graphics.Pixmaps;

public class Cursors{
    public static Color outlineColor = new Color(0, 0, 0, 0.1f);
    public static int cursorScaling = 4;

    public static Cursor arrow;
    public static Cursor ibeam;
    public static Cursor hand;

    private static ObjectMap<String, Cursor> customCursors = new ObjectMap<>();

    public static Cursor loadCursor(String name){
        Pixmap pixmap = new Pixmap(Core.files.internal("cursors/" + name + ".png"));
        Pixmap out = Pixmaps.outline(pixmap, outlineColor);
        out.setColor(Color.WHITE);
        Pixmap out2 = Pixmaps.scale(out, cursorScaling);

        if(!MathUtils.isPowerOfTwo(out2.getWidth())){
            Pixmap old = out2;
            out2 = Pixmaps.resize(out2, MathUtils.nextPowerOfTwo(out2.getWidth()), MathUtils.nextPowerOfTwo(out2.getWidth()));
            old.dispose();
        }

        out.dispose();
        pixmap.dispose();

        return Core.graphics.newCursor(out2, out2.getWidth() / 2, out2.getHeight() / 2);
    }

    public static void loadCustom(String name){
        customCursors.put(name, loadCursor(name));
    }

    public static void set(String cursorName){
        if(!customCursors.containsKey(cursorName))
            throw new IllegalArgumentException("No cursor with name '" + cursorName + "' exists!");
        Core.graphics.setCursor(customCursors.get(cursorName));
    }

    public static void setIbeam(){
        if(ibeam != null)
            Core.graphics.setCursor(ibeam);
        else
            Core.graphics.setSystemCursor(SystemCursor.Ibeam);
    }

    public static void setHand(){
        if(hand != null)
            Core.graphics.setCursor(hand);
        else
            Core.graphics.setSystemCursor(SystemCursor.Hand);
    }

    public static void restoreCursor(){
        if(arrow != null){
            Core.graphics.setCursor(arrow);
        }else{
            Core.graphics.setSystemCursor(SystemCursor.Arrow);
        }
    }

    public static void dispose(){
        if(arrow != null) arrow.dispose();
        if(ibeam != null) ibeam.dispose();
        if(hand != null) hand.dispose();

        if(customCursors != null){
            for(Cursor cursor : customCursors.values()){
                if(cursor != null){
                    cursor.dispose();
                }
            }
        }

        customCursors = new ObjectMap<>();
        arrow = ibeam = hand = null;
    }
}
