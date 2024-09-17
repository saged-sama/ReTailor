package com.javafest.Retailor.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javafest.Retailor.Service.FileService;
import com.javafest.Retailor.Service.ProductService;

@RestController
@RequestMapping("/api/files")
public class HomeController {

    @Autowired
    private FileService fileService;
    @Autowired
    private ProductService productService;

    @GetMapping("/images/test")
    public String helloController(){
        return "Hello!";
    }

    @GetMapping("/{tablename}/{recordId}/{fileName:.+}")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable String tablename, @PathVariable String recordId, @PathVariable String fileName) {
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

    @GetMapping("/home/products")
    public ResponseEntity<?> getProducts(
            @RequestParam(required = false) String search, // Search parameter for name or category
            @RequestParam(required = false) String sortKey, // Sort key for sorting
            @RequestParam(required = false) String sortDirection, // "asc" or "desc"
            @RequestParam(required = false) String category, // Category to filter products
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
        if (search != null) {
            return ResponseEntity.ok(productService.searchProduct(offset, pageSize, search));
        }

        if (category != null) {
            return ResponseEntity.ok(productService.allProductByCategory(category));
        }

        if (sortKey != null) {
            if ("asc".equalsIgnoreCase(sortDirection)) {
                return ResponseEntity.ok(productService.sortByParticularFieldAsc(offset, pageSize, sortKey));
            } else {
                return ResponseEntity.ok(productService.sortByParticularFieldDesc(offset, pageSize, sortKey));
            }
        }

        return ResponseEntity.ok(productService.displayProduct(offset, pageSize));
    }
}
