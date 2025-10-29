package com.ecommerce.kientv84.dtos.response;

import com.ecommerce.kientv84.dtos.response.objectRes.CategoryObjectResponse;
import com.ecommerce.kientv84.entites.SubCategoryEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SubCategoryResponse {
    private UUID id;
    private String subCategoryName;
    private String subCategoryCode;
    private String status;
    private CategoryObjectResponse category;
}
