package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TailorRepo extends JpaRepository<Tailor,String> {
    @SuppressWarnings("null")
    public Optional<Tailor> findById(String id);
    @SuppressWarnings("null")
    public List<Tailor> findAll();
    public void deleteById(@SuppressWarnings("null") String id);

    public List<Tailor> findAllByTailorStatus(TailorStatus tailorStatus);
    public Tailor findByUsers(Users users);
}
