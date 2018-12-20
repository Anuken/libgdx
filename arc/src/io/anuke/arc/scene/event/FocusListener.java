/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package io.anuke.arc.scene.event;

import io.anuke.arc.scene.Element;

/**
 * Listener for {@link FocusEvent}.
 * @author Nathan Sweet
 */
abstract public class FocusListener implements EventListener{
    public boolean handle(Event event){
        if(!(event instanceof FocusEvent)) return false;
        FocusEvent focusEvent = (FocusEvent)event;
        switch(focusEvent.type){
            case keyboard:
                keyboardFocusChanged(focusEvent, event.targetActor, focusEvent.focused);
                break;
            case scroll:
                scrollFocusChanged(focusEvent, event.targetActor, focusEvent.focused);
                break;
        }
        return false;
    }

    /** @param element The event target, which is the element that emitted the focus event. */
    public void keyboardFocusChanged(FocusEvent event, Element element, boolean focused){
    }

    /** @param element The event target, which is the element that emitted the focus event. */
    public void scrollFocusChanged(FocusEvent event, Element element, boolean focused){
    }

    /**
     * Fired when an element gains or loses keyboard or scroll focus. Can be cancelled to prevent losing or gaining focus.
     * @author Nathan Sweet
     */
    static public class FocusEvent extends Event{
        public boolean focused;
        public Type type;
        public Element relatedActor;

        public void reset(){
            super.reset();
            relatedActor = null;
        }

        /** @author Nathan Sweet */
        public enum Type{
            keyboard, scroll
        }
    }
}
