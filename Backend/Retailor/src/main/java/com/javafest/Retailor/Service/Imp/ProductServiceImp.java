package com.javafest.Retailor.Service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javafest.Retailor.Dto.CategorySalesDto;
import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Repository.ProductRepo;
import com.javafest.Retailor.Service.ProductService;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getById(String id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public String deleteProductById(String id) {
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
    public Page<ProductDto> displayTailorProduct(int offset, int pageSize, String id) {
        Page<Product> products= productRepo.findByTailorsIdAndAvailability(PageRequest.of(offset,pageSize),id,true);
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
    public Page<ProductDto> displayTailorSoldProduct(int offset, int pageSize,String id) {
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
    public List<CategorySalesDto> getCategorySalesInLastMonthByTailor(String tailorId) {
        List<Object[]> results = productRepo.findCategorySalesInLastMonthByTailor(tailorId);

        return results.stream()
                .map(row -> new CategorySalesDto(
                        (String) row[0],  // category
                        ((Number) row[1]).longValue()  // totalSales
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductDto> searchTailorProduct(String id,String parameter) {
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
    public Page<ProductDto> sortByParticularFieldAsc(int offset, int pageSize,String fieldName) {
        Page<Product> products= productRepo.findAllByAvailability(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,fieldName),true);
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
    public Page<ProductDto> sortByParticularFieldDesc(int offset, int pageSize,String fieldName) {
        Page<Product> products= productRepo.findAllByAvailability(PageRequest.of(offset,pageSize).withSort(Sort.Direction.DESC,fieldName),true);
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
    public Page<ProductDto> displayTailorSoldProductAsc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndSoldAtIsNotNull(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,sortKey),id);
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
    public Page<ProductDto> displayTailorSoldProductDesc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndSoldAtIsNotNull(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,sortKey),id);
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
    public Page<ProductDto> displayTailorProductAsc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndAvailability(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,sortKey),id,true);
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
    public Page<ProductDto> displayTailorProductDesc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndAvailability(PageRequest.of(offset,pageSize).withSort(Sort.Direction.DESC,sortKey),id,true);
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
    public List<ProductDto> getProductByCategory(String id,String category) {
        List<Product> products= productRepo.findByTailorsIdAndCategoryAndAvailability(id,category,true);
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
    public List<ProductDto> allProductByCategory(String category) {
        List<Product> products= productRepo.findByCategoryAndAvailability(category,true);
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
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }


}
