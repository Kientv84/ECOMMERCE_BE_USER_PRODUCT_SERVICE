package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CollectionRequest {
    @NotNull(message = "{collection.name.notnull}")
    private String collectionName;

    @NotNull(message = "{collection.collectionCode.notnull}")
    private String collectionCode;

    @NotNull(message = "{collection.description.notnull}")
    private String description;

    @NotNull(message = "{collection.status.notnull}")
    private String status;
}
