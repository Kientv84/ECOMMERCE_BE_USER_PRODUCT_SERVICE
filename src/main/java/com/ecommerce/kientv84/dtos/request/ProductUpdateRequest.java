package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProductUpdateRequest {
    private String brand;
    private String category;
    private String subCategory;
    private BigDecimal basePrice;
    private String status;
}

