package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Entity.SystemRole;
import com.ecommerce.kientv84.Respone.ResponeResult;

import java.util.List;

public interface SystemRoleService {

    ResponeResult getAllRole();

    ResponeResult getById(Long id);

    ResponeResult<SystemRole> createRole(SystemRole role);

    ResponeResult<SystemRole> updateRole(Long id, SystemRole updateData);

    ResponeResult deleteRole(List<Long> ids);
}
