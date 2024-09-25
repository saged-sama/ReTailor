package com.javafest.Retailor.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Measurements;

@Repository
public interface MeasurementsRepo extends JpaRepository<Measurements, String>{
    Page<Measurements> findAll(Specification<Measurements> spec, Pageable pageable);
}
