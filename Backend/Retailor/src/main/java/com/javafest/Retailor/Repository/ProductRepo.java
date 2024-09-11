package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    public Optional<Product> findById(Long id);
    public void deleteById(Long id);
    public Page<Product> findAllByAvailability(PageRequest pageRequest, Boolean available);
    public Page<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(PageRequest pageRequest,String param,String parameter);
    public Page<Product> findByTailorsIdAndSoldAtIsNotNull(PageRequest pageRequest, Long id);
    public List<Product> findByTailorsIdAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(Long id,String param, String parameter);
}
