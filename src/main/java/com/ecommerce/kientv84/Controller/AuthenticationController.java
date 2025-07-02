package com.ecommerce.kientv84.Controller;

import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Request.LoginRequest;
import com.ecommerce.kientv84.Respone.LoginResponse;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @Autowired

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService= authenticationService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authenticationService.login(request.getEmail(), request.getPassword());
    }
}
