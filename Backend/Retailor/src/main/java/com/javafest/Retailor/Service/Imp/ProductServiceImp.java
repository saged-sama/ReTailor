package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Repository.ProductRepo;
import com.javafest.Retailor.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public String deleteProductById(Long id) {
        productRepo.deleteById(id);
        return "Successfully Deleted";
    }

    @Override
    public Page<ProductDto> displayProduct(int offset, int pageSize) {
        Page<Product> products = productRepo.findAllByAvailability(PageRequest.of(offset, pageSize),true);
        List<ProductDto> productDtoList = products.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setAvailability(p.getAvailability());
            productDto.setDescription(p.getDescription());
            productDto.setCategory(p.getCategory());

            // Get the first image, if available
            if (!p.getImages().isEmpty()) {
                productDto.setImage(p.getImages().get(0));
            }

            productDto.setBasePrice(p.getBasePrice());
            productDto.setCreatedAt(p.getCreatedAt());
            productDto.setIsCustomizable(p.getIsCustomizable());
            productDto.setUpdatedAt(p.getUpdatedAt());

            // Get the first tailor, if available
            if (!p.getTailors().isEmpty()) {
                productDto.setTailors(p.getTailors().iterator().next());
            }

            productDto.setSoldAt(p.getSoldAt());

            return productDto;
        }).collect(Collectors.toList());

        // Return the DTO list as a Page
        return new PageImpl<>(productDtoList, PageRequest.of(offset, pageSize), products.getTotalElements());
    }

    @Override
    public Page<ProductDto> searchProduct(int offset, int pageSize,String parameter) {
        Page<Product> products= productRepo.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(PageRequest.of(offset,pageSize),parameter,parameter);
        List<ProductDto> productDtoList = products.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setAvailability(p.getAvailability());
            productDto.setDescription(p.getDescription());
            productDto.setCategory(p.getCategory());

            // Get the first image, if available
            if (!p.getImages().isEmpty()) {
                productDto.setImage(p.getImages().get(0));
            }

            productDto.setBasePrice(p.getBasePrice());
            productDto.setCreatedAt(p.getCreatedAt());
            productDto.setIsCustomizable(p.getIsCustomizable());
            productDto.setUpdatedAt(p.getUpdatedAt());

            // Get the first tailor, if available
            if (!p.getTailors().isEmpty()) {
                productDto.setTailors(p.getTailors().iterator().next());
            }

            productDto.setSoldAt(p.getSoldAt());

            return productDto;
        }).collect(Collectors.toList());

        // Return the DTO list as a Page
        return new PageImpl<>(productDtoList, PageRequest.of(offset, pageSize), products.getTotalElements());
    }

    @Override
    public Page<ProductDto> displayTailorSoldProduct(int offset, int pageSize,Long id) {
        Page<Product> products= productRepo.findByTailorsIdAndSoldAtIsNotNull(PageRequest.of(offset,pageSize),id);
        List<ProductDto> productDtoList = products.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setAvailability(p.getAvailability());
            productDto.setDescription(p.getDescription());
            productDto.setCategory(p.getCategory());

            // Get the first image, if available
            if (!p.getImages().isEmpty()) {
                productDto.setImage(p.getImages().get(0));
            }

            productDto.setBasePrice(p.getBasePrice());
            productDto.setCreatedAt(p.getCreatedAt());
            productDto.setIsCustomizable(p.getIsCustomizable());
            productDto.setUpdatedAt(p.getUpdatedAt());

            // Get the first tailor, if available
            if (!p.getTailors().isEmpty()) {
                productDto.setTailors(p.getTailors().iterator().next());
            }

            productDto.setSoldAt(p.getSoldAt());

            return productDto;
        }).collect(Collectors.toList());

        // Return the DTO list as a Page
        return new PageImpl<>(productDtoList, PageRequest.of(offset, pageSize), products.getTotalElements());
    }

    @Override
    public Page<ProductDto> trendingTailorCategory(int offset, int pageSize) {
        return null;
    }

    @Override
    public List<ProductDto> searchTailorProduct(Long id,String parameter) {
        List<Product> products= productRepo.findByTailorsIdAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(id,parameter,parameter);
        return products.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
            productDto.setAvailability(p.getAvailability());
            productDto.setDescription(p.getDescription());
            productDto.setCategory(p.getCategory());

            // Get the first image, if available
            if (!p.getImages().isEmpty()) {
                productDto.setImage(p.getImages().get(0));
            }

            productDto.setBasePrice(p.getBasePrice());
            productDto.setCreatedAt(p.getCreatedAt());
            productDto.setIsCustomizable(p.getIsCustomizable());
            productDto.setUpdatedAt(p.getUpdatedAt());

            // Get the first tailor, if available
            if (!p.getTailors().isEmpty()) {
                productDto.setTailors(p.getTailors().iterator().next());
            }

            productDto.setSoldAt(p.getSoldAt());

            return productDto;
        }).toList();
    }

    @Override
    public Page<ProductDto> sortByParticularFieldAsc(String fieldName) {
        return null;
    }

    @Override
    public Page<ProductDto> sortByParticularFieldDesc(String fieldName) {
        return null;
    }


}
