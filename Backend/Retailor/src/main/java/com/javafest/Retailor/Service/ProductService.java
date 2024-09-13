package com.javafest.Retailor.Service;

import com.javafest.Retailor.Dto.CategorySalesDto;
import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public Product getById(Long id);
    public String deleteProductById(Long id);
    public Page<ProductDto> displayProduct(int offset, int pageSize);
    public Page<ProductDto> searchProduct(int offset,int pageSize,String parameter);
    public Page<ProductDto> displayTailorProduct(int offset, int pageSize, Long id);
    public Page<ProductDto> displayTailorSoldProduct(int offset, int pageSize, Long id);
    public List<CategorySalesDto> getCategorySalesInLastMonthByTailor(Long tailorId);
    public List<ProductDto> searchTailorProduct(Long id,String parameter);
    public Page<ProductDto> sortByParticularFieldAsc(int offset, int pageSize,String fieldName);
    public Page<ProductDto> sortByParticularFieldDesc(int offset, int pageSize,String fieldName);
    public Page<ProductDto> displayTailorSoldProductAsc(int offset, int pageSize, Long id, String sortKey);
    public Page<ProductDto> displayTailorSoldProductDesc(int offset, int pageSize, Long id, String sortKey);
    public Page<ProductDto> displayTailorProductAsc(int offset, int pageSize, Long id,String sortKey);
    public Page<ProductDto> displayTailorProductDesc(int offset, int pageSize, Long id,String sortKey);
    public List<ProductDto> getProductByCategory(Long id,String category);
    public List<ProductDto> allProductByCategory(String category);
    public Product updateProduct(Product product);
}
