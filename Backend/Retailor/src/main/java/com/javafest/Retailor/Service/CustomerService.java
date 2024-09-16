package com.javafest.Retailor.Service;

import com.javafest.Retailor.Dto.CustomerDto;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;

public interface CustomerService {
    public CustomerDto saveCustomer(Customer customer);
    public CustomerDto findByCustomerId(String Id);
    public Customer getByUsers(Users users);
    public Customer updateCustomer(Customer customer);
}
