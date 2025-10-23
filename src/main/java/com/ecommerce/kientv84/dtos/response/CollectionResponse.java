package com.ecommerce.kientv84.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CollectionResponse {
    private UUID id;
    private String collectionName;
    private String collectionCode;
    private String description;
    private String status;
}
