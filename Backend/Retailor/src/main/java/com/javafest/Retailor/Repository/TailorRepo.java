package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TailorRepo extends JpaRepository<Tailor,Long> {

}
