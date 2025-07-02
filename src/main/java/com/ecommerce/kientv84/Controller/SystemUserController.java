package com.ecommerce.kientv84.Controller;

import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //toàn bộ method trong class sẽ tự động trả về JSON
@RequestMapping("/system_user")
public class SystemUserController {
    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/getAll")
    public ResponeResult<SystemUser> getAllUser() {
        return systemUserService.getAllUser();
    }

    @PostMapping("/create")
    public ResponeResult<SystemUser> createUser(@RequestBody SystemUser user) {
        return systemUserService.createUser(user);
    }

    @GetMapping("/getById/{id}")
    public ResponeResult<SystemUser> getById(@PathVariable Long id) {
        return systemUserService.getById(id);
    }

    @GetMapping("/getByCode/{code}")
    public ResponeResult<SystemUser> getById(@PathVariable String code) {
        return systemUserService.getByCode(code);
    }

    @PostMapping("/update/{id}")
    public ResponeResult<SystemUser> updateUser(@PathVariable Long id, @RequestBody SystemUser updatedData) {
        return systemUserService.updateUser(id, updatedData);
    }

    @PostMapping("/delete")
    public ResponeResult<String> deleteUser( @RequestBody List<Long> ids) {
        return systemUserService.deleteUser(ids);
    }
}
