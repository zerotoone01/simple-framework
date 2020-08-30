package com.demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyUtil {
    /**
     *
     * @see Proxy#newProxyInstance(java.lang.ClassLoader, java.lang.Class[], java.lang.reflect.InvocationHandler)
     *
     * @param targetObject 被代理类
     * @param handler
     * @param <T>
     * @return
     */
    public static <T> T newProxyInstance(T targetObject, InvocationHandler handler){
        //获取类加载器
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        return (T)Proxy.newProxyInstance(classLoader,interfaces,handler);
    }
}
