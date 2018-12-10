package com.badlogic.gdx;

import com.badlogic.gdx.collection.ObjectMap;

import static com.badlogic.gdx.Core.keybinds;

public abstract class Settings{
    private String appName;
    private ObjectMap<String, Object> defaults = new ObjectMap<>();
    private ObjectMap<String, Object> values = new ObjectMap<>();

    public void setAppName(String name){
        appName = name;
    }

    public String getAppName(){
        return appName;
    }

    /**Loads all values and keybinds.*/
    public void load(){
        keybinds.save();
        saveValues();
    }

    /**Saves all values and keybinds.*/
    public void save(){
        loadValues();
        keybinds.load();
    }

    /**Loads a settings file into {@link values} using the specified appName.*/
    public abstract void loadValues();

    /**Saves all entries from {@link values} into the correct location.*/
    public abstract void saveValues();

    /**Set up a list of defaults values.
     * Format: name1, default1, name2, default2, etc*/
    public void defaults(Object... objects){
        for(int i = 0; i < objects.length; i += 2){
            defaults.put((String) objects[i], objects[i + 1]);
        }
    }

    /**Clears all prefence values.*/
    public void clear(){
        values.clear();
    }

    public Object getDefault(String name){
        return defaults.get(name);
    }

    public boolean has(String name){
        return values.containsKey(name);
    }

    public float getFloat(String name, float def){
        return (float)values.get(name, def);
    }

    public int getInt(String name, int def){
        return (int)values.get(name, def);
    }

    public boolean getBool(String name, boolean def){
        return (boolean)values.get(name, def);
    }

    public byte[] getBytes(String name, byte[] def){
        return (byte[])values.get(name, def);
    }

    public String getString(String name, String def){
        return (String)values.get(name, def);
    }

    public float getFloat(String name){
        return getFloat(name, (float)defaults.get(name, 0f));
    }

    public int getInt(String name){
        return getInt(name, (int)defaults.get(name, 0));
    }

    public boolean getBool(String name){
        return getBool(name, (boolean)defaults.get(name, false));
    }

    public byte[] getBytes(String name){
        return getBytes(name, (byte[])defaults.get(name, null));
    }

    public String getString(String name){
        return getString(name, (String)defaults.get(name, null));
    }

    /**Stores an object in the preference map.*/
    public void put(String name, Object object){
        if(object instanceof Float || object instanceof Integer || object instanceof Boolean
            || object instanceof String || object instanceof byte[]){
            values.put(name, object);
        }else{
            throw new IllegalArgumentException("Invalid object stored: " + object);
        }
    }
}
