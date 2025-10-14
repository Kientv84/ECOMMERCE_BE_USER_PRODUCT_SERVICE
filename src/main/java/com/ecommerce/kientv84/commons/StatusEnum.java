package com.ecommerce.kientv84.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {


    ACTIVE("active"),
    UNACTIVE("non_active"),
    PENDING("pending");

    private final String status;
}
