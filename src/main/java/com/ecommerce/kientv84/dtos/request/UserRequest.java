package com.ecommerce.kientv84.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String password;
    private String email;
    private String phone;
}
