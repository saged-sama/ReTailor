package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Repository.ProductRepo;
import com.javafest.Retailor.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }
}
