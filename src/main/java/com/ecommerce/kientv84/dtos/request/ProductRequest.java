package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProductRequest {

    @NotNull(message =  "{product.productCode.notnul}")
    private String code;

    @NotNull(message =  "{product.brand.notnul}")
    private String brand;

    @NotNull(message =  "{product.category.notnul}")
    private String category;

    @NotNull(message =  "{product.subCategory.notnul}")
    private String subCategory;

    @NotNull(message =  "{product.basePrice.notnul}")
    private BigDecimal basePrice;

    @NotNull(message =  "{product.status.notnul}")
    private String status;
}
