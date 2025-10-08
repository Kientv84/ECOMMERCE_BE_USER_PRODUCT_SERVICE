package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse mapToUserEntity(UserEntity userEntity);
}
