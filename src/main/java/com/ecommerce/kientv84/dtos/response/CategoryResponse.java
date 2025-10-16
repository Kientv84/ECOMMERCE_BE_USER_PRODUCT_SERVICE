package com.ecommerce.kientv84.dtos.response;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponse {
    private UUID id;
    private String categoryName;
    private String categoryCode;
    private String status;
    private String description;
}
