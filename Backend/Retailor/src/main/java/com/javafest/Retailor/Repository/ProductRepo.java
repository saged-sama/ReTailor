package com.javafest.Retailor.Repository;

import com.javafest.Retailor.Entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    @SuppressWarnings("null")
    public Optional<Product> findById(String id);
    @SuppressWarnings("null")
    public void deleteById(String id);
    public Page<Product> findAllByTotalCountGreaterThan(PageRequest pageRequest, int totalCount);
    public Page<Product> findByTotalCountGreaterThanAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(PageRequest pageRequest,int totalCount,String param,String parameter);
    public Page<Product> findByTailorsIdAndTotalCountGreaterThan(PageRequest pageRequest,String Id, int totalCount);
    public Page<Product> findByTailorsIdAndSoldAtIsNotNull(PageRequest pageRequest, String id);
    public List<Product> findByTailorsIdAndTotalCountGreaterThanAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String id,int totalCount,String param, String parameter);
    public List<Product> findByTailorsIdAndCategoryAndTotalCountGreaterThan(String id, String param, int totalCount);
    public List<Product> findByCategoryAndTotalCountGreaterThan(String category, int totalCount);
    @Query(value = "SELECT p.category AS category, SUM(p.sold_count) AS totalSales " +
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