package com.javafest.Retailor.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    public Optional<Customer> findById(String id);
    public Customer findByUsers(Users user);
}
