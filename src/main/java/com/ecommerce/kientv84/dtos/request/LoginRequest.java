package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "{user.email.notnull}")
    private String email;
    @NotNull(message = "{user.phone.notnull}")
    private String password;

    public LoginRequest() {

    }
}
