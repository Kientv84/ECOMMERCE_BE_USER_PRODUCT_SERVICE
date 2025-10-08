package com.ecommerce.kientv84.dtos.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private int success;
    private String message;
    private String accessToken;
    private String name;

    public LoginResponse(int success, String message) {
        this.success = success;
        this.message = message;
    }
}
