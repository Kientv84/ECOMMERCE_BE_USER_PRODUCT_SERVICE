package com.ecommerce.kientv84.services;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.request.UserUpdateRequest;
import com.ecommerce.kientv84.dtos.request.search.UserSearchRequest;
import com.ecommerce.kientv84.dtos.response.PagedResponse;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.UserEntity;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.UUID;


public interface UserService {
    UserResponse createUser(UserRequest user);
    UserResponse getById(UUID id);
    UserResponse updateUser(UUID id, UserUpdateRequest updatedData);
    String deleteUser(List<UUID> ids);
    PagedResponse<UserResponse> searchUsers(UserSearchRequest req);
    //sub functions

}
