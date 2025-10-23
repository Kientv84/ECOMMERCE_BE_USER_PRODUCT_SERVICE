package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MaterialRequest {
    @NotNull(message = "{material.name.notnull}")
    private String materialName;

    @NotNull(message = "{material.materialCode.notnull}")
    private String materialCode;

    @NotNull(message = "{material.description.notnull}")
    private String description;

    @NotNull(message = "{material.status.notnull}")
    private String status;
}
