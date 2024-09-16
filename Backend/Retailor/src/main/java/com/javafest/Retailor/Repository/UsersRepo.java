package com.javafest.Retailor.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, String>, JpaSpecificationExecutor<Users> {
    @SuppressWarnings("null")
    @Override
    public Optional<Users> findById(String id);
    public Optional<Users> findByEmail(String email);
    @SuppressWarnings("null")
    @Override
    Page<Users> findAll(Specification<Users> spec, Pageable pageable);
}
