package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.ResponeResult;

import java.util.List;


public interface SystemUserService {
    ResponeResult<List<SystemUser>> getAllUser();
    ResponeResult<SystemUser> createUser();
    ResponeResult<SystemUser> updateUser();
    ResponeResult<SystemUser> deleteUser();

}
