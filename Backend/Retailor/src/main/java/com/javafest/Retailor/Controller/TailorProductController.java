package com.javafest.Retailor.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javafest.Retailor.Config.JwtService;
import com.javafest.Retailor.Dto.CategorySalesDto;
import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.Tailor;
import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.ProductService;
import com.javafest.Retailor.Service.TailorService;
import com.javafest.Retailor.Service.UsersService;

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
        product1.setAvailability(product.getAvailability());
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

    @GetMapping("/product/display/{offset}/{pageSize}/{id}")
    public ResponseEntity<Page<ProductDto>> getTailorProduct(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String id){
        return ResponseEntity.ok(productService.displayTailorProduct(offset,pageSize,id));
    }
    @GetMapping("/product/sortAsc/{offset}/{pageSize}/{id}/{param}")
    public ResponseEntity<Page<ProductDto>> getTailorProductAsc(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String id,@PathVariable String param){
        return ResponseEntity.ok(productService.displayTailorProductAsc(offset,pageSize,id,param));
    }
    @GetMapping("/product/sortDesc/{offset}/{pageSize}/{id}/{param}")
    public ResponseEntity<Page<ProductDto>> getTailorProductDesc(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String id, @PathVariable String param){
        return ResponseEntity.ok(productService.displayTailorProductDesc(offset,pageSize,id,param));
    }

    @GetMapping("/product/sold/{offset}/{pageSize}/{id}")
    public ResponseEntity<Page<ProductDto>> getSoldTailorProduct(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String id){
        return ResponseEntity.ok(productService.displayTailorSoldProduct(offset,pageSize,id));
    }

    @GetMapping("/product/search/{id}/{param}")
    public ResponseEntity<List<ProductDto>> getSearchTailorProduct(@PathVariable String id, @PathVariable String param){
        return ResponseEntity.ok(productService.searchTailorProduct(id,param));
    }

    @GetMapping("/soldProduct/sortAsc/{offset}/{pageSize}/{id}/{param}")
    public ResponseEntity<Page<ProductDto>> getSortedAscTailorProduct(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String id, @PathVariable String param){
        return ResponseEntity.ok(productService.displayTailorSoldProductAsc(offset,pageSize,id,param));
    }

    @GetMapping("/soldProduct/sortDesc/{offset}/{pageSize}/{id}/{param}")
    public ResponseEntity<Page<ProductDto>> getSortedDescTailorProduct(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String id, @PathVariable String param){
        return ResponseEntity.ok(productService.displayTailorSoldProductDesc(offset,pageSize,id,param));
    }

    @GetMapping("/product/category/{id}/{category}")
    public ResponseEntity<List<ProductDto>> getTailorProductByCategory(@PathVariable String id, @PathVariable String category){
        return ResponseEntity.ok(productService.getProductByCategory(id,category));
    }

    @GetMapping("/trendingProduct/{id}")
    public ResponseEntity<List<CategorySalesDto>> getTrendingCategory(@PathVariable String id){
        return ResponseEntity.ok(productService.getCategorySalesInLastMonthByTailor(id));
    }
}
