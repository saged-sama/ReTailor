package com.javafest.Retailor.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javafest.Retailor.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    @SuppressWarnings("null")
    @Override
    public Optional<Product> findById(String id);
    @Override
    public void deleteById(@SuppressWarnings("null") String id);
    public Page<Product> findAllByAvailability(PageRequest pageRequest, Boolean available);
    public Page<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(PageRequest pageRequest,String param,String parameter);
    public Page<Product> findByTailorsIdAndAvailability(PageRequest pageRequest,String Id, Boolean available);
    public Page<Product> findByTailorsIdAndSoldAtIsNotNull(PageRequest pageRequest, String id);
    public List<Product> findByTailorsIdAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String id,String param, String parameter);
    public List<Product> findByTailorsIdAndCategoryAndAvailability(String id, String param, Boolean available);
    public List<Product> findByCategoryAndAvailability(String category, Boolean available);
    @Query(value = "SELECT p.category AS category, COUNT(p.id) AS totalSales " +
            "FROM Product p " +
            "JOIN Tailor_Product tp ON p.id = tp.product_id " +
            "WHERE tp.tailor_id = :tailorId " +
            "AND p.sold_at IS NOT NULL " +
            "AND p.sold_at >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) " +
            "GROUP BY p.category " +
            "ORDER BY totalSales DESC",
            nativeQuery = true)
    public List<Object[]> findCategorySalesInLastMonthByTailor(@Param("tailorId") String tailorId);
}
