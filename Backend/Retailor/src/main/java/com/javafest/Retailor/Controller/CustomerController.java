package com.javafest.Retailor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.Service.UsersService;

@RestController
@RequestMapping("/api/collections/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/records")
    public ResponseEntity<Customer> getCustomerByUser(@RequestHeader("Authorization") String authHeader) throws Exception{
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }

        Users users= usersService.getByEmail(email);
        return ResponseEntity.ok(customerService.getByUsers(users));
    }
}
