package com.ecommerce.kientv84.services;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.request.UserUpdateRequest;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.UserEntity;


import java.util.List;
import java.util.UUID;


public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse createUser(UserRequest user);
    UserResponse getById(UUID id);
    UserResponse updateUser(UUID id, UserUpdateRequest updatedData);
    String deleteUser(List<UUID> ids);

    //sub functions

}
