package com.ecommerce.kientv84.dtos.request;

import com.ecommerce.kientv84.entites.SubCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubCategoryUpdateRequest {
    public String subCategoryName;
    public String subCategoryCode;
    private String status;
    private SubCategoryEntity category;
}
