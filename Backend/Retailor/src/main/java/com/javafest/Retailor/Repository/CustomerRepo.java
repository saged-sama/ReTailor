package com.javafest.Retailor.Repository;


import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    @SuppressWarnings("null")
    public Optional<Customer> findById(String id);
    public Customer findByUsers(Users user);
}