package com.demo.pattern.proxy.impl;

import com.demo.pattern.proxy.ToCPayment;

public class AliToCPaymentImpl implements ToCPayment {
    //静态代理， 硬编码方式
    ToCPayment toCPayment;
    public AliToCPaymentImpl(ToCPayment toCPayment){
        this.toCPayment = toCPayment;
    }
    @Override
    public void pay() {
        beforePay();
        toCPayment.pay();
        afterPay();
    }


    private void beforePay() {
        System.out.println("从招行取款");
    }

    private void afterPay() {
        System.out.println("支付给QQ会员");
    }
}
