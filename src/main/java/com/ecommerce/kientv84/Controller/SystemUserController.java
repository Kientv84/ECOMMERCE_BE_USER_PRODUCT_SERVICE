package com.ecommerce.kientv84.Controller;

import com.ecommerce.kientv84.Entity.SystemUser;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //toàn bộ method trong class sẽ tự động trả về JSON
@RequestMapping("/system_user")
public class SystemUserController {
    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @GetMapping("/getAll")
    public ResponeResult<List<SystemUser>> getAllUser() {
        return systemUserService.getAllUser();
    }
}
