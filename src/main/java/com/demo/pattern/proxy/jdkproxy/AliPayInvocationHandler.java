package com.demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用来封装通用的横切逻辑, 对被代理类做统一逻辑处理
 * {@link Proxy#newProxyInstance}的参数 {@link java.lang.reflect.InvocationHandler}可以用来实现具体的被代理类执行逻辑
 * @see Proxy#newProxyInstance(java.lang.ClassLoader, java.lang.Class[], java.lang.reflect.InvocationHandler)
 */
public class AliPayInvocationHandler implements InvocationHandler {
    //定义一个接收对象，用于接收传递过来的对象
    private Object targetObject;

    public AliPayInvocationHandler(Object targetObject){
        this.targetObject = targetObject;
    }

    //关联的这个实现类的方法被调用时将被执行
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforePay();
        Object result = method.invoke(targetObject,args);
        afterPay();
        return result;
    }

    private void beforePay() {
        System.out.println("从招行取款--InvocationHandler");
    }

    private void afterPay() {
        System.out.println("支付给QQ会员--InvocationHandler");
    }
}
