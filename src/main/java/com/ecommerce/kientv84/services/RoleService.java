package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.entites.RoleEntity;
import com.ecommerce.kientv84.dtos.response.ResponeResult;

import java.util.List;

public interface RoleService {

    List<RoleEntity> getAllRole();

    RoleEntity getById(Long id);

    RoleEntity createRole(RoleEntity role);

    RoleEntity updateRole(Long id, RoleEntity updateData);

    Boolean deleteRole(List<Long> ids);
}
