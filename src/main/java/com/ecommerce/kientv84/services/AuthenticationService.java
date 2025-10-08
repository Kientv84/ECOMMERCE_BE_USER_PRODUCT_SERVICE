package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(String email, String password) ;
}
