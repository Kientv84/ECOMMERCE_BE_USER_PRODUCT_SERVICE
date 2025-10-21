package com.ecommerce.kientv84.dtos.response;

import com.ecommerce.kientv84.entites.BrandEntity;
import com.ecommerce.kientv84.entites.CategoryEntity;

import com.ecommerce.kientv84.entites.SubCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class ProductResponse {
    private UUID id;
    private String productName;
    private String productCode;
    private BrandEntity brand;
    private CategoryEntity category;
    private SubCategoryEntity subCategory;
    private BigDecimal basePrice;
    private String status;
}