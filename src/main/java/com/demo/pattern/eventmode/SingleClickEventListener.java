package com.demo.pattern.eventmode;

public class SingleClickEventListener implements EventListener{
    @Override
    public void processEvent(Event event) {
        if("singleClick".equals(event.getType())){
            System.out.println(" single click");
        }
    }
}
