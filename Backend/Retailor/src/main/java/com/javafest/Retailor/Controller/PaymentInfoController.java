package com.javafest.Retailor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.PaymentInfo;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.PaymentInfoService;
import com.javafest.Retailor.Service.UsersService;

@RestController
@RequestMapping("/api/collections/paymentInfo")
public class PaymentInfoController {
    @Autowired
    private PaymentInfoService paymentInfoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/records/{userId}")
    public ResponseEntity<PaymentInfo> getMethodName(
            @PathVariable String userId,
            @RequestHeader("Authorization") String authToken
        )
    {
        String email = "";
        if(authToken != null && authToken.startsWith("Bearer ")){
            String jwt = authToken.substring(7);
            email = jwtService.extractUsername(jwt);
        } else {
            return ResponseEntity.badRequest().build();
        }
        
        Users user = usersService.getByEmail(email);
        PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoByUserId(userId);

        if(!user.getId().equals(paymentInfo.getUserId().getId())) {
            return ResponseEntity.badRequest().build();
        }

        paymentInfo.setUserId(user);

        return ResponseEntity.ok(paymentInfoService.getPaymentInfoByUserId(userId));
    }

    @PatchMapping("/records/{id}")
    public ResponseEntity<PaymentInfo> updatePaymentInfo(
            @RequestBody PaymentInfo paymentInfo,
            @PathVariable String id,
            @RequestHeader("Authorization") String authToken){
        String email = "";
        if(authToken != null && authToken.startsWith("Bearer ")){
            String jwt = authToken.substring(7);
            email = jwtService.extractUsername(jwt);
        } else {
            return ResponseEntity.badRequest().build();
        }

        Users user = usersService.getByEmail(email);
        PaymentInfo paymentInfoOld = paymentInfoService.getPaymentInfoById(id);

        if(!user.getId().equals(paymentInfoOld.getUserId().getId())) {
            return ResponseEntity.badRequest().build();
        }

        paymentInfo.setId(id);
        paymentInfo.setUserId(user);
        return ResponseEntity.ok(paymentInfoService.updatePaymentInfo(paymentInfo));
    }

    @PostMapping("/records")
    public ResponseEntity<PaymentInfo> savePaymentInfo(@RequestBody PaymentInfo paymentInfo, @RequestHeader("Authorization") String authToken) {
        String email = "";
        if(authToken != null && authToken.startsWith("Bearer ")){
            String jwt = authToken.substring(7);
            email = jwtService.extractUsername(jwt);
        } else {
            return ResponseEntity.badRequest().build();
        }
        
        Users user = usersService.getByEmail(email);
        paymentInfo.setUserId(user);

        return ResponseEntity.ok(paymentInfoService.addPaymentInfo(paymentInfo));
    }
    
}
