package com.demo.pattern.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AliPayMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        beforePay();
        Object result = methodProxy.invokeSuper(o,objects);
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
