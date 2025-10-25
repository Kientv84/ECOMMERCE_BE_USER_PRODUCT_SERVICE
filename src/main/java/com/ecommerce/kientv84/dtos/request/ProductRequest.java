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
public class ProductRequest {

    @NotNull(message =  "{product.productCode.notnul}")
    private String code;

    @NotNull(message =  "{product.brand.notnul}")
    private UUID brand;

    @NotNull(message =  "{product.category.notnull}")
    private UUID category;

    @NotNull(message =  "{product.subCategory.notnull}")
    private UUID subCategory;

    @NotNull(message =  "{product.basePrice.notnull}")
    private BigDecimal basePrice;

    @NotNull(message =  "{product.status.notnull}")
    private String status;

    private String description;

    private UUID collection;

    private Float discountPercent;

    private String origin;

    private UUID material;

    private String fitType;

    private String careInstruction;

    private String thumbnailUrl;

    private Double ratingAverage;

    private Integer ratingCount;

}
