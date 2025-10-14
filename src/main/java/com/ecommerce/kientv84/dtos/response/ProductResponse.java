package com.ecommerce.kientv84.dtos.response;

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
    private String brand;
    private String category;
    private String subCategory;
    private BigDecimal basePrice;
    private String status;
}