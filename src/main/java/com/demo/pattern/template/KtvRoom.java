package com.demo.pattern.template;

/**
 * 模板方法设计模式 举例说明
 *  KTV唱歌为例
 */
public abstract class KtvRoom {
    public void procedure(){
        openDevice();
        orderSong();
        orderExtra();
        pay();
    }

    //模板自带方法 -- KTV开机
    private void openDevice(){
        System.out.println("open ktv device");
    }
    //子类必须实现的方法，必须的选歌
    protected abstract void orderSong();
    //钩子方法， 子类可以实现也可以不实现， 额外开销视情况而定
    protected void orderExtra(){};
    //模板自带方法， 使用完需要付款
    private void pay(){
        System.out.println("order fee is .....");
    }

}
