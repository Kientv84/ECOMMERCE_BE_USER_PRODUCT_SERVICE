package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.LoginResponse;
import com.ecommerce.kientv84.Respone.ResponeResult;

public interface AuthenticationService {
    LoginResponse login(String email, String password) ;
}
