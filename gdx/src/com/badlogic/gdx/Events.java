package com.badlogic.gdx;


import com.badlogic.gdx.collection.Array;
import com.badlogic.gdx.collection.ObjectMap;
import com.badlogic.gdx.function.Consumer;

@SuppressWarnings("unchecked")
public class Events{
    private static ObjectMap<Class<? extends Event>, Array<Consumer<? extends Event>>> events = new ObjectMap<>();

    public static <T extends Event> void on(Class<T> type, Consumer<T> listener){
        if(events.get(type) == null)
            events.put(type, new Array<>());

        events.get(type).add(listener);
    }

    public static <T extends Event> void fire(T type){
        if(events.get(type.getClass()) == null)
            return;

        for(Consumer<? extends Event> event : events.get(type.getClass())){
            ((Consumer<T>)event).accept(type);
        }
    }

    public interface Event{

    }
}
