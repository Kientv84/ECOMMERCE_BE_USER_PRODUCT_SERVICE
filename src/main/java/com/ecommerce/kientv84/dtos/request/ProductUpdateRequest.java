package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class ProductUpdateRequest {
    private UUID brand;
    private String productCode;
    private UUID category;
    private UUID subCategory;
    private BigDecimal basePrice;
    private String description;
    private String status;
    private UUID collection;
    private UUID material;
    private Float discountPercent;
    private String origin;
    private String fitType;
    private Integer stock;
    private String careInstruction;
    private String thumbnailUrl;
    private Double ratingAverage;
    private Integer ratingCount;
}

