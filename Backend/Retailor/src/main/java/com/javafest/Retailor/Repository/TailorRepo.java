package com.javafest.Retailor.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;

@Repository
public interface TailorRepo extends JpaRepository<Tailor,String> {
    public Optional<Tailor> findById(String id);
    public List<Tailor> findAll();
    public void deleteById(String id);

    public List<Tailor> findAllByTailorStatus(TailorStatus tailorStatus);
    public Tailor findByUsers(Users users);
}
