package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepo extends JpaRepository<ProductSize, Long> {
    public ProductSize findByProductIdAndSizeAndQuantityGreaterThan(Long id, String size,int quantity);
    public List<ProductSize> findAllByProductIdAndQuantityGreaterThan(Long id,int quantity);
}

