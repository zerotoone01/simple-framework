package com.demo.pattern.proxy.impl;

import com.demo.pattern.proxy.ToBPayment;
import com.demo.pattern.proxy.ToCPayment;

public class AliToBPaymentImpl implements ToBPayment {
    //静态代理， 硬编码方式
    ToBPayment toBPayment;
    public AliToBPaymentImpl(ToBPayment toBPayment){
        this.toBPayment = toBPayment;
    }
    @Override
    public void pay() {
        beforePay();
        toBPayment.pay();
        afterPay();
    }


    private void beforePay() {
        System.out.println("从招行取款");
    }

    private void afterPay() {
        System.out.println("支付给QQ会员");
    }
}
