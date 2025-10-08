package com.ecommerce.kientv84.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum SuccessEnum {
    SUCCESS(1),
    ERROR(-1),
    DATA_EXISTED(2);

    private final int code;

}
