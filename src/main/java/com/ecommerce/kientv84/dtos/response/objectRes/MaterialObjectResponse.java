package com.ecommerce.kientv84.dtos.response.objectRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MaterialObjectResponse {
    private String materialName;
    private String materialCode;
    private String status;
}
