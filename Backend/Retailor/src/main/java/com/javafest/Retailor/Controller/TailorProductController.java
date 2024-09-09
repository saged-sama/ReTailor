package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Repository.UsersRepo;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.ProductService;
import com.javafest.Retailor.Service.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/collections/tailor")
public class TailorProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private TailorService tailorService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private FileService fileService;

    @PostMapping("/product/save")
    public ResponseEntity<Product> saveProduct(@ModelAttribute Product product,
                                               @RequestParam("avatars") MultipartFile[] files,
                                               @RequestHeader("Authorization") String authHeader) throws Exception {
        String email;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            email= jwtService.extractUsername(jwt);
        } else {
            throw new Exception("Invalid Authorization header.");
        }

        Users users = usersRepo.findByEmail(email).orElseThrow();

        Set<Tailor> tailorSet=new HashSet<>();
        tailorSet.add(tailorService.getByUser(users));
        List<String> Images= fileService.saveFiles(files);
        product.setTailors(tailorSet);
        product.setImages(Images);
        try {
             Product savedProduct = productService.save(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
