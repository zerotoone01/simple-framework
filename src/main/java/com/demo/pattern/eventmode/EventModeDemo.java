package com.demo.pattern.eventmode;

/**
 * 监听器模式 ：
 *
 * 观察者模式
 */
public class EventModeDemo {
    public static void main(String[] args) {
        //事件源
        EventSource eventSource = new EventSource();
        //监听器
        SingleClickEventListener singleClickEventListener = new SingleClickEventListener();
        DoubleClickEventListener doubleClickEventListener = new DoubleClickEventListener();
        Event event = new Event();
        event.setType("singleClick");
        //监听器注册
        eventSource.register(singleClickEventListener);
        eventSource.register(doubleClickEventListener);
        //发布事件
        eventSource.publishEvent(event);

    }
}
