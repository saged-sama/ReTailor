package com.javafest.Retailor.Controller;

import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class HomeController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ProductService productService;

    @GetMapping("/images/test")
    public String helloController(){
        return "Hello!";
    }

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable String fileName) {
        try {
            // Use the fileName to load the file (the service will prepend "images/" if necessary)
            byte[] file = fileService.loadFileAsResource(fileName);

            // Get the file's MIME type dynamically
            Path filePath = Paths.get("images").resolve(fileName).normalize();
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            // Set the appropriate content type based on the file's MIME type
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(mimeType));

            return new ResponseEntity<>(file, headers, HttpStatus.OK);

        } catch (IOException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/display/{offset}/{pageSize}")
    public ResponseEntity<Page<ProductDto>> getAllProduct(@PathVariable int offset, @PathVariable int pageSize){

        return ResponseEntity.ok(productService.displayProduct(offset,pageSize));
    }

    @GetMapping("/product/search/{offset}/{pageSize}/{param}")
    public ResponseEntity<Page<ProductDto> > searchAllProduct(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String param){
        return ResponseEntity.ok(productService.searchProduct(offset,pageSize,param));
    }

    @GetMapping("/product/sortAsc/{offset}/{pageSize}/{param}")
    public ResponseEntity<Page<ProductDto> > sortAllProductAsc(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String param){
        return ResponseEntity.ok(productService.sortByParticularFieldAsc(offset,pageSize,param));
    }

    @GetMapping("/product/sortDesc/{offset}/{pageSize}/{param}")
    public ResponseEntity<Page<ProductDto> > sortAllProductDesc(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String param){
        return ResponseEntity.ok(productService.sortByParticularFieldDesc(offset,pageSize,param));
    }

    @GetMapping("/product/category/{param}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String param){
        return ResponseEntity.ok(productService.allProductByCategory(param));
    }
}
