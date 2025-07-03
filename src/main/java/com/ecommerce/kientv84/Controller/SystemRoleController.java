package com.ecommerce.kientv84.Controller;

import com.ecommerce.kientv84.Entity.SystemRole;
import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Toàn bộ method trong class sẽ trả về json, khác @Controller ở chỗ là Controller thì được hiểu là trang web sd html
//@RestController + @ResponseBody (ngầm) để: Tự động trả về các status theo chuẩn mà không cần quy định
// Tự động chuyển hóa kết quả thành chuỗi json
// Tự động gán Content-Type: application/json

@RequestMapping("/system_role")
public class SystemRoleController {
    @Autowired
    private SystemRoleService systemRoleService;

    public SystemRoleController(SystemRoleService systemRoleService) {
        this.systemRoleService = systemRoleService;
    }


    @GetMapping("/getAll")
    public ResponeResult getAllRole() {
        return  systemRoleService.getAllRole();
    }

    @GetMapping("/getById/{id}")
    public ResponeResult getById(@PathVariable Long id) {
        return systemRoleService.getById(id);
    }

    @PostMapping("/create")
    public ResponeResult<SystemRole> createRole(@RequestBody SystemRole role) {
        return systemRoleService.createRole(role);
    }

    @PostMapping("/update/{id}")
    public ResponeResult<SystemRole> updateRole(@PathVariable Long id,@RequestBody SystemRole updateData) {
        return systemRoleService.updateRole(id, updateData);
    }

    @PostMapping("/delete") //Tại sao sd post mà không dùng delete method
    //POST là method luôn mong đợi có body. Nên khi: chọn Body → raw → JSON
    // ==> Postman tự động gán Content-Type: application/json vào header để yêu cầu body!!!
    public ResponeResult deleteRole(@RequestBody List<Long> ids) {
        return systemRoleService.deleteRole(ids);
    }
}
