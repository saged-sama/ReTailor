package com.javafest.Retailor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.PaymentInfo;
import com.javafest.Retailor.Entity.Users;

@Repository
public interface PaymentInfoRepo extends JpaRepository<PaymentInfo, String> {
    
    public PaymentInfo findByUserId(Users userId);
}
