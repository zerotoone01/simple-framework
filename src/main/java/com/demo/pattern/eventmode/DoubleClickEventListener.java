package com.demo.pattern.eventmode;

public class DoubleClickEventListener implements EventListener{
    @Override
    public void processEvent(Event event) {
        if("doubleClick".equals(event.getType())){
            System.out.println(" double click");
        }
    }
}
