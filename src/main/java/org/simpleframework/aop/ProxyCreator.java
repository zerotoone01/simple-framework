package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class ProxyCreator {
    /**
     * 创建动态搭理对象返回
     * @param targetClass
     * @param methodInterceptor
     * @return
     */
    public static  Object creatProxy(Class<?> targetClass, MethodInterceptor methodInterceptor){
        return Enhancer.create(targetClass,methodInterceptor);
    }
}
