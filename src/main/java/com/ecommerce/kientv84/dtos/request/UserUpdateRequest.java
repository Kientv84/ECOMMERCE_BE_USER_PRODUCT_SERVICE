package com.ecommerce.kientv84.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private String userName;

    private String userEmail;

    private String userPhoneNumber;

    private String status;
}
