package com.ecommerce.kientv84.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {

    }
}
