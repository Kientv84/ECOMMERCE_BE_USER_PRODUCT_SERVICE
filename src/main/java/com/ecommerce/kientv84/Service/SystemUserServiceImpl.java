package com.ecommerce.kientv84.Service;

import com.ecommerce.kientv84.Commons.CKConstant.CkResults;
import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Responsitory.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;


    @Override
    public ResponeResult<List<SystemUser>> getAllUser() {

        try {
            List<SystemUser> data = systemUserRepository.findAll();

            if (!data.isEmpty()) {
                ResponeResult<List<SystemUser>> listUser = new ResponeResult<>(CkResults.SUCCESS, "Get all success !!", data);
                return listUser;
            } else {

                return new ResponeResult<>(CkResults.ERROR, "Not found any user!!");
            }




        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }

    @Override
    public ResponeResult<SystemUser> createUser() {
        return null;
    }

    @Override
    public ResponeResult<SystemUser> updateUser() {
        return null;
    }

    @Override
    public ResponeResult<SystemUser> deleteUser() {
        return null;
    }
}
