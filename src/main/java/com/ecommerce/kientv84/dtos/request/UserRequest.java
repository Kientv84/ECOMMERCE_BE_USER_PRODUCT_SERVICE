package com.ecommerce.kientv84.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    @NotNull(message =  "{user.name.notnul}")
    private String name;

    @NotNull(message =  "{user.role.notnul}")
    private Long role;

    @NotNull(message =  "{user.password.notnul}")
    private String password;

    @NotNull(message =  "{user.email.notnul}")
    private String email;

    @NotNull(message =  "{user.phone.notnul}")
    private String phone;
}
