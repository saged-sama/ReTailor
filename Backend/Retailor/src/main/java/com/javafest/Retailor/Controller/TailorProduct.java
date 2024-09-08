package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collections/tailor")
public class TailorProduct {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/save")
    ResponseEntity<Product> saveProduct(Product product){
        try {
             Product savedProduct = productService.save(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
