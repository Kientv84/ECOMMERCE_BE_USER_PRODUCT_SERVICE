package com.ecommerce.kientv84.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CollectionUpdateRequest {
    private String collectionName;
    private String collectionCode;
    private String description;
    private String status;
}

