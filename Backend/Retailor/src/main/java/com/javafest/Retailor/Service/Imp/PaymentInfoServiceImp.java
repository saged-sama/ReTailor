package com.javafest.Retailor.Service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Entity.PaymentInfo;
import com.javafest.Retailor.Repository.PaymentInfoRepo;
import com.javafest.Retailor.Service.PaymentInfoService;
import com.javafest.Retailor.Service.UsersService;

@Service
public class PaymentInfoServiceImp implements PaymentInfoService {

    @Autowired
    private PaymentInfoRepo paymentInfoRepo;
    @Autowired
    private UsersService usersService;

    @Override
    public PaymentInfo addPaymentInfo(PaymentInfo paymentInfo) {
        return paymentInfoRepo.save(paymentInfo);
    }

    @Override
    public PaymentInfo getPaymentInfoByUserId(String userId) {
        System.out.println("userIddddd: " + userId);
        return paymentInfoRepo.findByUserId(usersService.getById(userId));
    }

    @Override
    public PaymentInfo updatePaymentInfo(PaymentInfo paymentInfo) {
        return paymentInfoRepo.save(paymentInfo);
    }

    @Override
    public PaymentInfo getPaymentInfoById(String id) {
        return paymentInfoRepo.findById(id).get();
    }
    
}
