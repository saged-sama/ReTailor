package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.CategorySalesDto;
import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.ProductSize;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private UsersService usersService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ProductSizeService productSizeService;

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

        Users users = usersService.getByEmail(email);

        //System.out.println(users);

        Set<Tailor> tailorSet=new HashSet<>();
        tailorSet.add(tailorService.getByUser(users));
        List<String> Images= fileService.saveFiles(files);
        for (Tailor t : tailorSet) {
            product.addTailor(t);// This ensures that the product is added to the tailor as well
        }
        product.setSoldCount(0);
        int ct=0;
        for (ProductSize p: product.getSizes()){
            ct+=p.getQuantity();
            p.setProduct(product);
        }
        product.setTotalCount(ct);

        //System.out.println(product.getTailors());
        product.setImages(Images);
        try {
             Product savedProduct = productService.save(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/update")
    public ResponseEntity<Product> updateProduct(@ModelAttribute Product product,
                                                 @RequestParam("avatars") MultipartFile[] files) throws Exception{
        //System.out.println("Vitore");
        Product product1 = productService.getById(product.getId());
        product1.setName(product1.getName());
        product1.setDescription(product.getDescription());
        product1.setCategory(product.getCategory());
        product1.setSoldAt(product.getSoldAt());
        for(var image: product1.getImages()){
            fileService.deleteFile(image);
        }
        //System.out.println("Aro vitore");
        List<String> Images= fileService.saveFiles(files);
        product1.setImages(Images);
        product1.setIsCustomizable(product.getIsCustomizable());
        product1.setBasePrice(product.getBasePrice());
        try {
            Product savedProduct = productService.save(product1);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions and return a 500 (INTERNAL SERVER ERROR) status
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable String id) throws IOException {
        Product product = productService.getById(id);
        for(var image: product.getImages()){
            fileService.deleteFile(image);
        }
        for (Tailor t : product.getTailors()) {
            product.removeTailor(t);// This ensures that the product is added to the tailor as well
        }
        return productService.deleteProductById(id);
    }

    @GetMapping("/product/filter")
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String id, // Tailor ID
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String parameter, // Search parameter for name or category
            @RequestParam(required = false) Boolean soldOnly, // Filter sold products
            @RequestParam(required = false) String sortKey, // Sort key for sorting
            @RequestParam(required = false) String sortDirection, // "asc" or "desc"
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize);

        // Apply sorting if sortKey is provided
        if (sortKey != null) {
            Sort.Direction direction = (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageRequest = PageRequest.of(offset, pageSize, Sort.by(direction, sortKey));
        }

        // Determine which service method to call based on provided parameters
        if (id != null) {
            if (soldOnly != null) {
                return ResponseEntity.ok(productService.displayTailorSoldProductAsc(offset, pageSize, id, sortKey));
            }
            if (parameter != null) {
                return ResponseEntity.ok(productService.searchTailorProduct(id, parameter));
            }
            if (category != null) {
                return ResponseEntity.ok(productService.getProductByCategory(id, category));
            }
            if (sortKey != null) {
                return ResponseEntity.ok(productService.displayTailorProductAsc(offset, pageSize, id, sortKey));
            }
            return ResponseEntity.ok(productService.displayTailorProduct(offset, pageSize, id));
        }

        if (parameter != null) {
            return ResponseEntity.ok(productService.searchProduct(offset, pageSize, parameter));
        }

        if (category != null) {
            return ResponseEntity.ok(productService.allProductByCategory(category));
        }

        return ResponseEntity.ok(productService.displayProduct(offset, pageSize));
    }

    @GetMapping("/sizes/{id}")
    public ResponseEntity<List<ProductSize>> getProductSizes(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductSizeForAProduct(id));
    }

    @GetMapping("/trending/{id}")
    public ResponseEntity<List<CategorySalesDto>> getTrendingCategory(@PathVariable String id) {
        return ResponseEntity.ok(productService.getCategorySalesInLastMonthByTailor(id));
    }
}