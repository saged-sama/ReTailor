package com.javafest.Retailor.Dto;

import com.javafest.Retailor.Entity.ProductSize;
import com.javafest.Retailor.Entity.Tailor;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String Id;
    private String name;
    private String description;
    private String category;
    private String image;
    private Boolean isCustomizable;
    private Double basePrice;
    private Tailor tailors;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Date soldAt;
    private int totalCount;
    private int soldCount;
    private List<ProductSize> sizes;
}