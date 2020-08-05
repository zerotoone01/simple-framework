package com.demo.pattern.callback;

/**
 * 监听者模式 --回调
 */
public class CallBackDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am sleeping...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i was callback!");
            }
        });

        thread.start();
    }

}
