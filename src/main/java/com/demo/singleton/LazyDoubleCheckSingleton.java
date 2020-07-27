package com.demo.singleton;

/**
 * 懒汉模式
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;
    //私有，不允许被外界直接实例调用
    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance(){

        if(instance==null){
            synchronized (LazyDoubleCheckSingleton.class){
                if(instance==null){
                    // 1.分配内存空间  2.初始化对象 3.设置instance指向分配的内存地址
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
