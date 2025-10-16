package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryRequest {
    @NotNull(message = "{category.name.notnull}")
    private String categoryName;

    @NotNull(message = "{category.code.notnull}")
    private String categoryCode;

    @NotNull(message = "{category.status.notnull}")
    private String status;

    @NotNull(message = "{category.description.notnull}")
    private String description;
}
