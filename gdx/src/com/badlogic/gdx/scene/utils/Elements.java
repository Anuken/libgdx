package com.badlogic.gdx.scene.utils;

import com.badlogic.gdx.function.BooleanConsumer;
import com.badlogic.gdx.function.Consumer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scene.ui.CheckBox;
import com.badlogic.gdx.scene.ui.ImageButton;
import com.badlogic.gdx.scene.ui.TextButton;
import com.badlogic.gdx.scene.ui.TextField;

import static com.badlogic.gdx.Core.scene;

public class Elements{

    public static CheckBox newCheck(String text, BooleanConsumer listener){
        CheckBox button = new CheckBox(text);
        if(listener != null)
            button.changed(() -> listener.accept(button.isChecked()));
        return button;
    }

    public static TextButton newButton(String text, Runnable listener){
        TextButton button = new TextButton(text);
        if(listener != null)
            button.changed(listener);

        return button;
    }

    public static TextButton newButton(String text, String style, Runnable listener){
        TextButton button = new TextButton(text, style);
        if(listener != null)
            button.changed(listener);

        return button;
    }

    public static ImageButton newImageButton(String icon, Runnable listener){
        ImageButton button = new ImageButton(scene.skin.getDrawable(icon));
        if(listener != null)
            button.changed(listener);
        return button;
    }

    public static ImageButton newImageButton(String icon, float size, Runnable listener){
        ImageButton button = new ImageButton(scene.skin.getDrawable(icon));
        button.resizeImage(size);
        if(listener != null)
            button.changed(listener);
        return button;
    }

    public static ImageButton newImageButton(String style, String icon, float size, Runnable listener){
        ImageButton button = new ImageButton(icon, style);
        button.resizeImage(size);
        if(listener != null)
            button.changed(listener);
        return button;
    }

    public static ImageButton newImageButton(String icon, float size, Color color, Runnable listener){
        ImageButton button = new ImageButton(scene.skin.getDrawable(icon));
        button.resizeImage(size);
        button.getImage().setColor(color);
        if(listener != null)
            button.changed(listener);
        return button;
    }

    public static ImageButton newToggleImageButton(String icon, float size, boolean on, BooleanConsumer listener){
        ImageButton button = new ImageButton(icon, "toggle");
        button.setChecked(on);
        button.resizeImage(size);
        button.clicked(() -> listener.accept(button.isChecked()));
        return button;
    }

    public static TextField newField(String text, Consumer<String> listener){
        TextField field = new TextField(text);
        if(listener != null)
            field.changed(() -> listener.accept(field.getText()));

        return field;
    }
}
