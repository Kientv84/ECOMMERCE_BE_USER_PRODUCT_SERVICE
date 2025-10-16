package com.ecommerce.kientv84.dtos.request;

import com.ecommerce.kientv84.entites.CategoryEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubCategoryRequest {
    @NotNull(message = "{sub.category.name.notnull}")
    public String subCategoryName;

    @NotNull(message = "{sub.category.code.notnull}")
    public String subCategoryCode;

    @NotNull(message = "{sub.category.status.notnull}")
    private String status;

    @NotNull(message = "{sub.category.category.notnull}")
    private CategoryEntity category;
}
