package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Commons.CKConstant.CkResults;
import com.ecommerce.kientv84.Config.JwtUtil;
import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.LoginResponse;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Responsitory.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(String email, String password) {
        try {
            SystemUser user = systemUserRepository.findBySystemUserEmail(email);
            if (user == null) {
                return new LoginResponse(CkResults.ERROR, "Not found user with email");
            }

            if (!passwordEncoder.matches(password, user.getSystemUserPassword())) {
                return new LoginResponse(CkResults.ERROR, "Wrong password");
            }

            String token = jwtUtil.generateToken(user.getSystemUserEmail());

            return new LoginResponse(
                    CkResults.SUCCESS,
                    "Login successfully",
                    token,
                    user.getSystemUserName()
            );

        } catch (Exception e) {
            return new LoginResponse(CkResults.ERROR, "Error: " + e.getMessage());
        }
    }


}
