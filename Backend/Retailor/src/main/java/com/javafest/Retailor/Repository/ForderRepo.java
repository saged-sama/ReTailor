package com.javafest.Retailor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Users;


@Repository
public interface ForderRepo extends JpaRepository<Forder, String> {
    public List<Forder> findByUserId(Users userId);
}
