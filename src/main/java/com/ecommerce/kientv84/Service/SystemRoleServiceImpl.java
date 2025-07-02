package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Commons.CKConstant.CkResults;
import com.ecommerce.kientv84.Entity.SystemRole;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Responsitory.SystemRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleRepository systemRoleRepository;

    @Override
    public ResponeResult getAllRole() {
        System.out.println("This is get all System Role Call.....");

            List<SystemRole> data = systemRoleRepository.findAll();

            if (data.isEmpty()) {
                return new ResponeResult(CkResults.ERROR, "Not found any role!") ;
            } else {
                return new ResponeResult(CkResults.SUCCESS, "Get all roles successfully!!", data);
            }
    }

    @Override
    public ResponeResult getById(Long id) {
        System.out.println("This is api getById system role call....");
            SystemRole role = systemRoleRepository.findById(id).orElse(null);

        if (role != null) {
            return new  ResponeResult(CkResults.SUCCESS, "Get role successfully!", role);
        } else {
            return new  ResponeResult(CkResults.ERROR, "Get role faild!");
        }
    }

    @Override
    public ResponeResult<SystemRole> createRole(SystemRole role) {
        System.out.println("Called createRole API...");

        try {
            SystemRole newRole = systemRoleRepository.save(role);

            return new ResponeResult(
                    CkResults.SUCCESS,
                    "Create role successfully!!!",
                    newRole
            );
        } catch (Exception ex) {
            return new ResponeResult(
                    CkResults.ERROR,
                    "Create role failed: " + ex.getMessage()
            );
        }
    }


    @Override
    public ResponeResult<SystemRole> updateRole(Long id) {
        return null;
    }

    @Override
    public ResponeResult deleteRole() {
        return null;
    }
}

