package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.ProductSize;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepo extends JpaRepository<ProductSize, String> {
    public ProductSize findByProductIdAndSizeAndQuantityGreaterThan(String id, String size,int quantity);
    public List<ProductSize> findAllByProductIdAndQuantityGreaterThan(String id,int quantity);
}

