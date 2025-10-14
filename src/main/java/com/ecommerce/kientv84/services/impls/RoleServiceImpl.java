package com.ecommerce.kientv84.services.impls;
import com.ecommerce.kientv84.entites.RoleEntity;
import com.ecommerce.kientv84.respositories.RoleRepository;
import com.ecommerce.kientv84.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getAllRole() {
        return List.of();
    }

    @Override
    public RoleEntity getById(Long id) {
        return null;
    }

    @Override
    public RoleEntity createRole(RoleEntity role) {
        return null;
    }

    @Override
    public RoleEntity updateRole(Long id, RoleEntity updateData) {
        return null;
    }

    @Override
    public Boolean deleteRole(List<Long> ids) {
        return null;
    }
}

