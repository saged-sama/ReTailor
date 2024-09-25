package com.javafest.Retailor.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Bargain;
import com.javafest.Retailor.Entity.Forder;
import com.javafest.Retailor.Entity.Tailor;

@Repository
public interface BargainRepo extends JpaRepository<Bargain, String> {
    public List<Bargain> findByForder(Forder forder);
    public List<Bargain> findByTailor(Tailor tailor);
}
