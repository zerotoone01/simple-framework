package com.demo.pattern.proxy;

import com.demo.pattern.proxy.cglib.AliPayMethodInterceptor;
import com.demo.pattern.proxy.cglib.CglibUtil;
import com.demo.pattern.proxy.impl.*;
import com.demo.pattern.proxy.jdkproxy.AliPayInvocationHandler;
import com.demo.pattern.proxy.jdkproxy.JdkDynamicProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 主要用于创建动态代理类
 * @see Proxy
 *
 * 用来统一管理横切逻辑Aspect的，真正的代理类是由 Proxy 创建出来的
 * @see InvocationHandler
 *
 * 静态代理和动态代理的区别：
 *
 * 1.按照代理的创建时期，代理类可以分为两种：
 *
 * 静态：由程序员创建代理类或特定工具自动生成源代码再对其编译。在程序运行前代理类的.class文件就已经存在了。
 *
 * 动态：在程序运行时运用反射机制动态创建而成。
 *
 *
 *
 */
public class ProxyDemo {

    public static void main1(String[] args) {
        //用户只关系 pay()
        ToCPayment toCProxy = new AliToCPaymentImpl(new ToCPaymentImpl());
        toCProxy.pay();
        ToBPayment toBProxy = new AliToBPaymentImpl(new ToBPaymentImpl());
        toBProxy.pay();
        //静态代理的劣势： 每新增一个类型，即使处理逻辑都相同，都需要单独去构造一次
    }

    public static void main2(String[] args) {
        //采用如下动态代理 AliToBPaymentImpl AliToCPaymentImpl 的 before和after 方法都可以去掉
        ToCPayment toCPayment = new AliToCPaymentImpl(new ToCPaymentImpl());
        InvocationHandler invocationHandler = new AliPayInvocationHandler(toCPayment);
        ToCPayment toCProxy =JdkDynamicProxyUtil.newProxyInstance(toCPayment,invocationHandler);
        toCProxy.pay();

        ToBPayment toBPayment = new AliToBPaymentImpl(new ToBPaymentImpl());
        InvocationHandler invocationHandlerToB = new AliPayInvocationHandler(toBPayment);
        ToBPayment toBProxy =JdkDynamicProxyUtil.newProxyInstance(toBPayment,invocationHandlerToB);
        toBProxy.pay();
    }

    public static void main(String[] args) {
//        CommonPayment commonPayment = new CommonPayment();
//        AliPayInvocationHandler aliPayInvocationHandler = new AliPayInvocationHandler(commonPayment);
//        CommonPayment commonPaymentProxy = JdkDynamicProxyUtil.newProxyInstance(commonPayment, aliPayInvocationHandler);
//        commonPaymentProxy.pay();
        // Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.demo.pattern.proxy.impl.CommonPayment
        //	at com.demo.pattern.proxy.ProxyDemo.main(ProxyDemo.java:44)
        // 错误原因：CommonPayment 没有去实现任何接口，证实 JDK 动态代理是让动态代理类来实现和被代理类一样的接口，从而去替代被代理类去工作的？

        CommonPayment commonPayment = new CommonPayment();
        AliPayMethodInterceptor aliPayMethodInterceptor = new AliPayMethodInterceptor();
        CommonPayment cglibProxy = CglibUtil.createProxy(commonPayment, aliPayMethodInterceptor);
        cglibProxy.pay();

    }
}
