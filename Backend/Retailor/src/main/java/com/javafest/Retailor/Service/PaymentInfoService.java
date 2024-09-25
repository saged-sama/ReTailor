package com.javafest.Retailor.Service;

import com.javafest.Retailor.Entity.PaymentInfo;

public interface PaymentInfoService {
    public PaymentInfo addPaymentInfo(PaymentInfo paymentInfo);
    public PaymentInfo getPaymentInfoByUserId(String userId);
    public PaymentInfo updatePaymentInfo(PaymentInfo paymentInfo);
    public PaymentInfo getPaymentInfoById(String id);
}
