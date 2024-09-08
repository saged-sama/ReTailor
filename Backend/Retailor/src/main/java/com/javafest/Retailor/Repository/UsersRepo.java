package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    public Optional<Users> findById(Long id);
    public Optional<Users> findByEmail(String email);
}
