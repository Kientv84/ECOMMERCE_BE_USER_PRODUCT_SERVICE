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
    private UUID category;
    private UUID subCategory;
    private BigDecimal basePrice;
    private String status;
}

