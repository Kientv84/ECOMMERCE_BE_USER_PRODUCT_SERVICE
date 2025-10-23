package com.ecommerce.kientv84.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MaterialUpdateRequest {
    private String materialName;
    private String materialCode;
    private String description;
    private String status;
}
