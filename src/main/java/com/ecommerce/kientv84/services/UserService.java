package com.ecommerce.kientv84.services;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.UserEntity;


import java.util.List;


public interface UserService {
    List<UserEntity> getAllUser();
    UserResponse createUser(UserRequest user);
    UserEntity getById(Long id);
    UserEntity getByCode(String code);
    UserEntity updateUser(Long id, UserEntity updatedData);
    Boolean deleteUser(List<Long> ids);
    List<UserEntity> getAllByRole(Long roleId);

    //sub functions

     String generateCode();

}
