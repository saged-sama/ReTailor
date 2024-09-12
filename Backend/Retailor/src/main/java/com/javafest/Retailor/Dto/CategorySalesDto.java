package com.javafest.Retailor.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySalesDto {
    private String category;
    private Long totalSales;
}
