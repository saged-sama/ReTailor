package com.javafest.Retailor.Service;

import com.javafest.Retailor.Dto.CustomerDto;
import com.javafest.Retailor.Entity.Customer;

import java.util.Optional;

public interface CustomerService {
    public CustomerDto save(Customer customer);
    public CustomerDto findByCustomerId(Long Id);
}
