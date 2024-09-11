package com.javafest.Retailor.Service;

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
    public Page<ProductDto> displayTailorSoldProduct(int offset, int pageSize, Long id);
    public Page<ProductDto> trendingTailorCategory(int offset, int pageSize);
    public List<ProductDto> searchTailorProduct(Long id,String parameter);
    public Page<ProductDto> sortByParticularFieldAsc(String fieldName);
    public Page<ProductDto> sortByParticularFieldDesc(String fieldName);
}
