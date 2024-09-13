package com.javafest.Retailor.Service;

import com.javafest.Retailor.Dto.CustomerDto;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;

import java.util.Optional;

public interface CustomerService {
    public CustomerDto saveCustomer(Customer customer);
    public CustomerDto findByCustomerId(Long Id);
    public Customer getByUsers(Users users);
    public Customer updateCustomer(Customer customer);
}
