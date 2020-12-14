package com.demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyUtil {
    /**
     *
     * @see Proxy#newProxyInstance(java.lang.ClassLoader, java.lang.Class[], java.lang.reflect.InvocationHandler)
     *
     *  绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
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
