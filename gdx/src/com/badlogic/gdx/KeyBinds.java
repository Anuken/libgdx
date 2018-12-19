package com.badlogic.gdx;


import com.badlogic.gdx.collection.ObjectMap;
import com.badlogic.gdx.collection.ObjectMap.Entry;
import com.badlogic.gdx.collection.OrderedMap;
import com.badlogic.gdx.input.InputDevice;
import com.badlogic.gdx.input.InputDevice.DeviceType;
import com.badlogic.gdx.input.KeyCode;
import com.badlogic.gdx.math.Mathf;

import static com.badlogic.gdx.Core.input;
import static com.badlogic.gdx.Core.settings;

/**
 * Stores keybinds.
 * <p>
 * This is done by first splitting keybinds into separate sections by name.
 * Further, the keybinds are split by device type (keyboard, controller), and then by name.
 * <p>
 * Example hierarchy:
 * <p>
 * Section (default)
 * Device (keyboard)
 * jump = KeyCode.SPACE
 * move = Axis(KeyCode.LEFT, KeyCode.RIGHT)
 * Device (controller)
 * jump = KeyCode.CONTROLLER_A
 * move = Axis(CONTROLLER_L_STICK_HORIZONTAL_AXIS)
 * <p>
 * Section (player2)
 * ...etc
 */
public class KeyBinds{
    /** Default section used. Usually player 1. */
    private Section defaultSection = new Section("default");
    /** A cache for storing defaults for key definitions. */
    private ObjectMap<KeyBind, ObjectMap<DeviceType, Axis>> defaultCache = new ObjectMap<>();
    /** All key definitions supplied. */
    private KeyBind[] definitions;
    /** All sections supplied. */
    private Section[] sections;

    public void setDefaults(KeyBind[] defs, Section... sectionArr){
        definitions = defs;
        sections = new Section[sectionArr.length + 1];
        sections[0] = defaultSection;
        System.arraycopy(sectionArr, 0, sections, 1, sectionArr.length);

        for(KeyBind def : defs){
            defaultCache.put(def, new ObjectMap<>());
            for(DeviceType type : DeviceType.values()){
                defaultCache.get(def).put(type,
                def.defaultValue(type) instanceof Axis ?
                (Axis)def.defaultValue(type) : new Axis((KeyCode)def.defaultValue(type)));
            }
        }
    }

    /** Saves the internal keybind data to settings' map. Does not call settings.save(). */
    void save(){
        for(Section sec : sections){
            for(DeviceType type : DeviceType.values()){
                for(Entry<KeyBind, Axis> entry : sec.binds.get(type).entries()){
                    String rname = "keybind-" + sec.name + "-" + type.name() + "-" + entry.key.name();
                    save(entry.value, rname);
                }
            }
            settings.put(sec.name + "-last-device-type", input.getDevices().indexOf(sec.device, true));
        }
    }

    /** Loads the internal keybind data from settings' map. Does not call settings.load(). */
    void load(){
        for(Section sec : sections){
            for(DeviceType type : DeviceType.values()){
                for(KeyBind def : definitions){
                    String rname = "keybind-" + sec.name + "-" + type.name() + "-" + def.name();

                    Axis loaded = load(rname);

                    if(loaded != null){
                        sec.binds.get(type).put(def, loaded);
                    }
                }
            }

            sec.device = input.getDevices().get(Mathf.clamp(settings.getInt(sec.name + "-last-device-type", 0), 0, input.getDevices().size - 1));
        }
    }

    /**
     * Resets all keybinds to their default values.
     * Call Core.settings.save() to flush the changes afterwards.
     */
    public void resetToDefaults(){
        for(Section sec : sections){
            sec.binds.clear();
        }
    }

    private void save(Axis axis, String name){
        settings.put(name + "-single", axis.key != null);

        if(axis.key != null){
            settings.put(name + "-key", axis.key.ordinal());
        }else{
            settings.put(name + "-min", axis.min.ordinal());
            settings.put(name + "-max", axis.max.ordinal());
        }
    }

    private Axis load(String name){
        if(settings.getBool(name + "-single")){
            KeyCode key = KeyCode.byOrdinal(settings.getInt(name + "-key", KeyCode.UNSET.ordinal()));
            return key == KeyCode.UNSET ? null : new Axis(key);
        }else{
            KeyCode min = KeyCode.byOrdinal(settings.getInt(name + "-min", KeyCode.UNSET.ordinal()));
            KeyCode max = KeyCode.byOrdinal(settings.getInt(name + "-max", KeyCode.UNSET.ordinal()));
            return min == KeyCode.UNSET || max == KeyCode.UNSET ? null : new Axis(min, max);
        }
    }

    public Section[] getSections(){
        return sections;
    }

    public KeyBind[] getKeybinds(){
        return definitions;
    }

    public Axis get(KeyBind name){
        return get(defaultSection, name);
    }

    public Axis get(Section section, KeyBind def){
        return get(section, section.device.type(), def);
    }

    public Axis get(Section section, DeviceType type, KeyBind def){
        if(section.binds.containsKey(type) && section.binds.get(type).containsKey(def)){
            return section.binds.get(type).get(def);
        }
        return defaultCache.get(def).get(type);
    }

    /** Represents an axis or a keycode. */
    public interface KeybindValue{

    }

    /**
     * An interface to store type-safe keybind definitions.
     * This interface is supposed to be implemented by an enum.
     */
    public interface KeyBind{
        /** The unique name of this keycode. Usually implemented automatically by the enum type. */
        String name();

        /** The default implementation returns can return the same default value for each device type. */
        KeybindValue defaultValue(DeviceType type);

        /**
         * The 'new' category under which this keybind will be displayed in a keybind dialog.
         * Implementation of this method is optional.
         */
        default String category(){
            return null;
        }
    }

    /**
     * A section represents a set of input binds, like controls for a specific player.
     * Each section has a device, which may be a controller or keyboard, and a name (for example, "player2")
     * The default section uses a keyboard and is named 'default'.
     */
    public class Section{
        public final String name;
        public ObjectMap<DeviceType, OrderedMap<KeyBind, Axis>> binds = new ObjectMap<>();
        public InputDevice device = input.getKeyboard();

        Section(String name){
            this.name = name;
        }
    }

    public class Axis implements KeybindValue{
        public KeyCode min, max;
        public KeyCode key;

        /** Cosntructor for axis-type keys only. */
        public Axis(KeyCode key){
            this.key = key;
            this.min = max = null;
        }

        /** Constructor for keyboards/mice, or multiple buttons on a controller. */
        public Axis(KeyCode min, KeyCode max){
            this.min = min;
            this.max = max;
            this.key = null;
        }
    }

}
