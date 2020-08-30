package com.demo.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 用来封装通用的横切逻辑
 */
public class AliPayInvocationHandler implements InvocationHandler {
    //定义一个接收对象，用于接收传递过来的对象
    private Object targetObject;

    public AliPayInvocationHandler(Object targetObject){
        this.targetObject = targetObject;
    }
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
