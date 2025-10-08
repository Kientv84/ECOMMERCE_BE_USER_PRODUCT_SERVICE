package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.config.JWT.JwtUtil;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.dtos.response.LoginResponse;
import com.ecommerce.kientv84.respositories.UserRepository;
import com.ecommerce.kientv84.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    

   @Override
    public LoginResponse login(String username, String password) {
       return null;
   }


}
