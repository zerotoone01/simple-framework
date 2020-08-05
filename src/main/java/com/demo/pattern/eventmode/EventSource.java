package com.demo.pattern.eventmode;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件源
 */
public class EventSource {
    private List<EventListener> listenerList = new ArrayList<>();

    public void register(EventListener listener){
        listenerList.add(listener);
    }

    /**
     * 事件发布
     * @param event
     */
    public void publishEvent(Event event){
        for (EventListener listener: listenerList){
            listener.processEvent(event);
        }
    }
}
