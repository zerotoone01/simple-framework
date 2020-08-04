package com.demo.pattern.template;

public class RoomForChineseSinger extends KtvRoom{
    @Override
    protected void orderSong() {
        System.out.println("RoomForChineseSinger: order chinese song");
    }
    @Override
    protected void orderExtra(){
        System.out.println("RoomForChineseSinger: order extra");
    };
}
