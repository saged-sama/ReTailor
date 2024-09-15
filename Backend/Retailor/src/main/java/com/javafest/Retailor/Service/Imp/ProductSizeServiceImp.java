package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.ProductSize;
import com.javafest.Retailor.Repository.ProductSizeRepo;
import com.javafest.Retailor.Service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeServiceImp implements ProductSizeService {
    @Autowired
    private ProductSizeRepo productSizeRepo;
    @Override
    public ProductSize saveProductSize(ProductSize productSize) {
        return productSizeRepo.save(productSize);
    }

    @Override
    public ProductSize getProductSizeByProductIdAndSize(Long id,String size) {
        return productSizeRepo.findByProductIdAndSizeAndQuantityGreaterThan(id,size,0);
    }

    @Override
    public List<ProductSize> getProductSizeByProductId(Long id) {
        return productSizeRepo.findAllByProductIdAndQuantityGreaterThan(id,0);
    }
}
