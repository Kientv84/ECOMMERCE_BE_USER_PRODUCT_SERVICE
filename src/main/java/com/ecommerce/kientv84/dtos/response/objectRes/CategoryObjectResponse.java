package com.ecommerce.kientv84.dtos.response.objectRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryObjectResponse {
    private String categoryName;
    private String categoryCode;
    private String status;
}
