package com.ecommerce.kientv84.Controller;

import com.ecommerce.kientv84.Respone.ResponeResult;
import com.ecommerce.kientv84.Service.SystemEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Đánh dấu là một controller của một dự án mà toàn bộ method trong class sẽ tự động trả về JSON,
//khác @Controller ở chỗ là Controller thì được hiểu là trang web sd html
// Trong @RestController bao gồm @ResponseBody (ngầm) ==> Sẽ tự động trả về các status vd 2xx 4xx
@RequestMapping("/system_employee")
public class SystemEmployeeController {
    @Autowired
    private SystemEmployeeService systemEmployeeService;

    public SystemEmployeeController(SystemEmployeeService systemEmployeeService) {
        this.systemEmployeeService = systemEmployeeService;
    }

    @GetMapping("/get-all")
    public ResponeResult getAllEmployee() {
        return systemEmployeeService.getAllEmployee();
    }
}
