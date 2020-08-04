package com.demo.pattern.template;

public class TemplateDemo {

    public static void main(String[] args) {
        RoomForAmericanSinger roomForAmericanSinger = new RoomForAmericanSinger();
        roomForAmericanSinger.procedure();
        System.out.println(">>>>>>>>>>>>>>>>");
        RoomForChineseSinger roomForChineseSinger = new RoomForChineseSinger();
        roomForChineseSinger.procedure();
    }
}
