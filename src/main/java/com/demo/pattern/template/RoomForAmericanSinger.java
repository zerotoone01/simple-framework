package com.demo.pattern.template;

public class RoomForAmericanSinger extends KtvRoom{
    @Override
    protected void orderSong() {
        System.out.println("RoomForChineseSinger: order english song");
    }
}
