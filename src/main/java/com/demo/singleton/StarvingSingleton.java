package com.demo.singleton;

/**
 * 恶汉模式
 */
public class StarvingSingleton {
    private static final StarvingSingleton starvingSingleton= new StarvingSingleton();
    private StarvingSingleton(){}
    public static StarvingSingleton getInstance(){
        return starvingSingleton;
    }
}
