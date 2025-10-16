package com.ecommerce.kientv84.dtos.response;

import com.ecommerce.kientv84.entites.SubCategoryEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubCategoryResponse {
    public String subCategoryName;
    public String subCategoryCode;
    private String status;
    private SubCategoryEntity category;
}
