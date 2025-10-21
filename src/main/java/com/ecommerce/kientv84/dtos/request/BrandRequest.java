package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BrandRequest {
    @NotNull(message = "{brand.name.notnull}")
    private String brandName;
    @NotNull(message = "{brand.code.notnull}")
    private String brandCode;
    @NotNull(message = "{brand.status.notnull}")
    private String status;
    @NotNull(message = "{brand.description.notnull}")
    private String description;
}
