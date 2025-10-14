package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.request.LoginRequest;
import com.ecommerce.kientv84.dtos.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request) ;
}
