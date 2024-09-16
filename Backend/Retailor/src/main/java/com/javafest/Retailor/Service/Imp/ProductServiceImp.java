package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Dto.CategorySalesDto;
import com.javafest.Retailor.Dto.ProductDto;
import com.javafest.Retailor.Entity.Product;
import com.javafest.Retailor.Entity.ProductSize;
import com.javafest.Retailor.Repository.ProductRepo;
import com.javafest.Retailor.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Product getById(String id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public String deleteProductById(String id) {
        productRepo.deleteById(id);
        return "Successfully Deleted";
    }

    private Page<ProductDto> helpToGetPage(int offset, int pageSize,Page<Product> products){
        List<ProductDto> productDtoList = products.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
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
            productDto.setTotalCount(p.getTotalCount());
            productDto.setSizes(p.getSizes());
            productDto.setSoldCount(p.getSoldCount());

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

    private List<ProductDto> helpToGetList(List<Product> products){
        return products.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(p.getId());
            productDto.setName(p.getName());
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
            productDto.setTotalCount(p.getTotalCount());
            productDto.setSizes(p.getSizes());
            productDto.setSoldCount(p.getSoldCount());
            // Get the first tailor, if available
            if (!p.getTailors().isEmpty()) {
                productDto.setTailors(p.getTailors().iterator().next());
            }

            productDto.setSoldAt(p.getSoldAt());

            return productDto;
        }).toList();
    }

    @Override
    public Page<ProductDto> displayProduct(int offset, int pageSize) {
        Page<Product> products = productRepo.findAllByTotalCountGreaterThan(PageRequest.of(offset, pageSize),0);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> searchProduct(int offset, int pageSize,String parameter) {
        Page<Product> products= productRepo.findByTotalCountGreaterThanAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(PageRequest.of(offset,pageSize),0,parameter,parameter);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> displayTailorProduct(int offset, int pageSize, String id) {
        Page<Product> products= productRepo.findByTailorsIdAndTotalCountGreaterThan(PageRequest.of(offset,pageSize),id,0);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> displayTailorSoldProduct(int offset, int pageSize,String id) {
        Page<Product> products= productRepo.findByTailorsIdAndSoldAtIsNotNull(PageRequest.of(offset,pageSize),id);
        return helpToGetPage(offset,pageSize,products);
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
        List<Product> products= productRepo.findByTailorsIdAndTotalCountGreaterThanAndNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(id,0,parameter,parameter);
        return helpToGetList(products);
    }

    @Override
    public Page<ProductDto> sortByParticularFieldAsc(int offset, int pageSize,String fieldName) {
        Page<Product> products= productRepo.findAllByTotalCountGreaterThan(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,fieldName),0);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> sortByParticularFieldDesc(int offset, int pageSize,String fieldName) {
        Page<Product> products= productRepo.findAllByTotalCountGreaterThan(PageRequest.of(offset,pageSize).withSort(Sort.Direction.DESC,fieldName),0);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> displayTailorSoldProductAsc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndSoldAtIsNotNull(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,sortKey),id);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> displayTailorSoldProductDesc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndSoldAtIsNotNull(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,sortKey),id);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> displayTailorProductAsc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndTotalCountGreaterThan(PageRequest.of(offset,pageSize).withSort(Sort.Direction.ASC,sortKey),id,0);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public Page<ProductDto> displayTailorProductDesc(int offset, int pageSize, String id, String sortKey) {
        Page<Product> products= productRepo.findByTailorsIdAndTotalCountGreaterThan(PageRequest.of(offset,pageSize).withSort(Sort.Direction.DESC,sortKey),id,0);
        return helpToGetPage(offset,pageSize,products);
    }

    @Override
    public List<ProductDto> getProductByCategory(String id,String category) {
        List<Product> products= productRepo.findByTailorsIdAndCategoryAndTotalCountGreaterThan(id,category,0);
        return helpToGetList(products);
    }

    @Override
    public List<ProductDto> allProductByCategory(String category) {
        List<Product> products= productRepo.findByCategoryAndTotalCountGreaterThan(category,0);
        return helpToGetList(products);
    }

    @Override
    public Product updateProduct(Product product) {

        return productRepo.save(product);
    }

    @Override
    public List<ProductSize> getProductSizeForAProduct(String id) {
        Product product= productRepo.findById(id).orElseThrow(() -> new RuntimeException("No Product Found!"));
        return product.getSizes();
    }


}