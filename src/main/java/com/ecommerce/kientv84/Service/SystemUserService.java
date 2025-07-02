package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.ResponeResult;

import java.util.List;
import java.util.Optional;


public interface SystemUserService {
    ResponeResult<SystemUser> getAllUser();
    ResponeResult<SystemUser> createUser(SystemUser user);
    ResponeResult<SystemUser> getById(Long id);
    ResponeResult<SystemUser> getByCode(String code);
    ResponeResult<SystemUser> updateUser(Long id, SystemUser updatedData);
    ResponeResult<String> deleteUser(List<Long> ids);


    //sub functions

     String generateCode();

}
