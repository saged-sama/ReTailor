package com.javafest.Retailor.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long Id;
    private String name;
    private String description;
    private String category;
    private Set<String> images;
    private Boolean isCustomizable;
    private Double basePrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Date soldAt;
    private Boolean availability;
}
