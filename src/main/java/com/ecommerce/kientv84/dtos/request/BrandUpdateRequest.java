package com.ecommerce.kientv84.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BrandUpdateRequest {
    private String brandName;
    private String brandCode;
    private String status;
    private String description;
}
