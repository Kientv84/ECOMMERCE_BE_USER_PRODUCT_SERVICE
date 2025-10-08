package com.ecommerce.kientv84.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponeResult<T> {
    private int success;
    private String message;
    private T data;

    public ResponeResult(int success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponeResult(int success, String message) {
        this.success = success;
        this.message = message;
    }
}
