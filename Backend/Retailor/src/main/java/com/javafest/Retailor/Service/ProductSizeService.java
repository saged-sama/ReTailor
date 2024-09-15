package com.javafest.Retailor.Service;

import com.javafest.Retailor.Entity.ProductSize;

import java.util.List;

public interface ProductSizeService {
    public ProductSize saveProductSize(ProductSize productSize);
    public ProductSize getProductSizeByProductIdAndSize(Long id,String size);
    public List<ProductSize> getProductSizeByProductId(Long id);
}
