package com.javafest.Retailor.Service.Imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Dto.CustomerDto;
import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Repository.CustomerRepo;
import com.javafest.Retailor.Service.CustomerService;
import com.javafest.Retailor.utils.EntityUpdate;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public CustomerDto saveCustomer(Customer customer) {
        Customer customer1= customerRepo.save(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setGender(customer.getGender());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhone(customer.getPhone());
        customerDto.setAvatar(customer.getAvatar());
        customerDto.setUsers(customer.getUsers());
        customerDto.setCreatedAt(customer.getCreatedAt());
        customerDto.setUpdatedAt(customer.getUpdatedAt());
        customerDto.setMeasurements(customer.getMeasurements());

        return customerDto;
    }

    @Override
    public CustomerDto findByCustomerId(String Id) {
        Optional <Customer> customer = customerRepo.findById(Id);
        CustomerDto customerDto = new CustomerDto();

        if(customer.isPresent()) {
            customerDto.setId(customer.get().getId());
            customerDto.setName(customer.get().getName());
            customerDto.setGender(customer.get().getGender());
            customerDto.setAddress(customer.get().getAddress());
            customerDto.setPhone(customer.get().getPhone());
            customerDto.setAvatar(customer.get().getAvatar());
            customerDto.setUsers(customer.get().getUsers());
            customerDto.setCreatedAt(customer.get().getCreatedAt());
            customerDto.setUpdatedAt(customer.get().getUpdatedAt());
            customerDto.setMeasurements(customer.get().getMeasurements());
        }

        return customerDto;
    }

    @Override
    public Customer getByUsers(Users users) {
        return customerRepo.findByUsers(users);
    }

    @Override
    public Customer updateCustomer(Customer customer, String customerId) {
        Customer existingCustomer = customerRepo.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        EntityUpdate.merge(existingCustomer, customer);

        return customerRepo.save(existingCustomer);
    }
}