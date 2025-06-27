package com.ecommerce.kientv84.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ecommerce.kientv84.Commons.CKConstant.CkResults;
import com.ecommerce.kientv84.Config.SercurityConfig;
import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Responsitory.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    // Inject các bean
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public ResponeResult<List<SystemUser>> getAllUser() {

        List<SystemUser> data = systemUserRepository.findAll();

        if (!data.isEmpty()) {
            ResponeResult<List<SystemUser>> listUser = new ResponeResult<>(CkResults.SUCCESS, "Get all success !!", data);
            return listUser;
        } else {

            return new ResponeResult<>(CkResults.ERROR, "Not found any user!!");
        }

    }

    @Override
    public ResponeResult<SystemUser> createUser(SystemUser user) {
        System.out.println("Called createUser API...");

        try {
            //Hash password
            String hashPassword = passwordEncoder.encode(user.getSystemUserPassword());

            user.setSystemUserCode(generateCode());
            user.setSystemUserPassword(hashPassword);

            SystemUser savedUser = systemUserRepository.save(user);
            return new ResponeResult<>(CkResults.SUCCESS, "Create user successfully!", savedUser);

        } catch (Exception e) {
            return new ResponeResult<>(CkResults.ERROR, "Create user failed: " + e.getMessage());
        }
    }

    @Override
    public ResponeResult<SystemUser> getById(Long id) {
        System.out.println("Called getById API...");

        try {
            SystemUser user = systemUserRepository.findById(id).orElse(null);

            if (user != null) {
                return new ResponeResult<>(CkResults.SUCCESS, "Get user by id successfully", user);
            } else {
                return new ResponeResult<>(CkResults.ERROR, "User not found with id = " + id);
            }

        } catch (Exception e) {
            return new ResponeResult<>(CkResults.ERROR, "No found user: " + e.getMessage());
        }
    }

    @Override
    public ResponeResult<SystemUser> getByCode(String code) {
        System.out.println("Called getBy code API...");

        try {
            SystemUser user = systemUserRepository.findBySystemUserCode(code);

            return new ResponeResult<>(CkResults.SUCCESS, "Get user by code successfully", user);
        } catch (Exception e) {
            return new ResponeResult<>(CkResults.ERROR, "No found user: " + e.getMessage());
        }
    }

    @Override
    public ResponeResult<SystemUser> updateUser(Long id, SystemUser updatedData) {
        System.out.println("Called updateUser API...");

        try {
            SystemUser user = systemUserRepository.findById(id).orElse(null);

            if (user == null) {
                return new ResponeResult<>(CkResults.ERROR, "Not found anny user!!!");
            } else {
                if(updatedData.getSystemUserName() != null && !updatedData.getSystemUserName().equals((user.getSystemUserName()))) {
                    user.setSystemUserName(updatedData.getSystemUserName());
                }

                // Thêm thông tin cập nhật
                user.setUpdateBy("admin"); // hoặc lấy từ user context
                user.setUpdateDate(new Date());

                SystemUser savedUser = systemUserRepository.save(user);

                return new ResponeResult<>(CkResults.SUCCESS, "Update user successfully!", savedUser);
            }

        } catch (Exception e) {
            return new ResponeResult<>(CkResults.ERROR, "error: " + e.getMessage());
        }
    }

    @Override
    public ResponeResult<String> deleteUser(List<Long> ids) {
        System.out.println("Called api delte user ....");
        try {
            List<SystemUser> users = systemUserRepository.findAllById(ids);

            if (users.isEmpty()) {
                return new ResponeResult<>(CkResults.ERROR, "No users found for provided IDs.");
            } else {
                systemUserRepository.deleteAll(users);

                return new ResponeResult<>(CkResults.SUCCESS, "Deleted " + users.size() + " user(s) successfully!");
            }
        } catch (Exception e) {
            return new ResponeResult<>(CkResults.ERROR, "error: " + e.getMessage());
        }
    }


    //sub function implements

    public String generateCode() {
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

        String code = today.format(formatter);

        long count = systemUserRepository.count() + 1;

        return "GS" + code + String.format("%03d", count);
    }
}
