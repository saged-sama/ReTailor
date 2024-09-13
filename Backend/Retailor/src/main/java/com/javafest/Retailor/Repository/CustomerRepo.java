package com.javafest.Retailor.Repository;


import com.javafest.Retailor.Entity.Customer;
import com.javafest.Retailor.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    public Optional<Customer> findById(Long id);
    public Customer findByUsers(Users user);
}
