package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.SuccessEnum;
import com.ecommerce.kientv84.config.JWT.JwtUtil;
import com.ecommerce.kientv84.dtos.request.LoginRequest;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.dtos.response.LoginResponse;
import com.ecommerce.kientv84.respositories.UserRepository;
import com.ecommerce.kientv84.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            UserEntity user = userRepository.findByUserEmail(request.getEmail());

            Boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getUserPassword());

            String token = jwtUtil.generateToken(user.getUserEmail());

            if (isMatch) {
                return new LoginResponse(SuccessEnum.SUCCESS.getCode(), "Login successfully", token, user.getUserName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new LoginResponse(SuccessEnum.ERROR.getCode(), "Login fail");
    }
}
