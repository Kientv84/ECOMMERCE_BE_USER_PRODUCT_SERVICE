package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //toàn bộ method trong class sẽ tự động trả về JSON, còn @Controller là được hiểu là trang web thuần html css
// Trong @RestController bao gồm @ResponseBody (ngầm) ==> Sẽ tự động trả về các status vd 2xx 4xx
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return  ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity<UserEntity> getById(@PathVariable String code) {
        return ResponseEntity.ok(userService.getByCode(code));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedData) {
        return ResponseEntity.ok(userService.updateUser(id, updatedData));
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteUser( @RequestBody List<Long> ids) {
        return ResponseEntity.ok(userService.deleteUser(ids));
    }

    @GetMapping("/getAllByRole/{id}")
    public ResponseEntity<List<UserEntity>> getAllByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(userService.getAllByRole(roleId));
    }
}
