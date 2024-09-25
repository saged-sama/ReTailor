package com.javafest.Retailor.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;

@Repository
public interface TailorRepo extends JpaRepository<Tailor,String> {
    @SuppressWarnings("null")
    public Optional<Tailor> findById(String id);
    @SuppressWarnings("null")
    public List<Tailor> findAll();
    public void deleteById(@SuppressWarnings("null") String id);

    public List<Tailor> findAllByTailorStatus(TailorStatus tailorStatus);
    public Tailor findByUsers(Users users);

    @Query("SELECT t from Tailor t where t.tailorStatus = :status and t.name like :pattern%")
    public List<Tailor> findByStatusAndByUserPattern(@Param("status") TailorStatus status, @Param("pattern") String pattern);
}
