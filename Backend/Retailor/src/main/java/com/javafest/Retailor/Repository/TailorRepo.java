package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TailorRepo extends JpaRepository<Tailor,Long> {
    public Optional<Tailor> findById(Long id);
    public List<Tailor> findAll();
    public void deleteById(Long id);

    public List<Tailor> findAllByTailorStatus(TailorStatus tailorStatus);
    public Tailor findByUsers(Users users);
}
