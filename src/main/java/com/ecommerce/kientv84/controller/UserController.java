package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.request.UserUpdateRequest;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //toàn bộ method trong class sẽ tự động trả về JSON, còn @Controller là được hiểu là trang web thuần html css
// Trong @RestController bao gồm @ResponseBody (ngầm) ==> Sẽ tự động trả về các status vd 2xx 4xx
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/accounts")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/account")
    public ResponseEntity<UserResponse> createUser( @Valid  @RequestBody UserRequest user) {
        return  ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }


    @PostMapping("/account/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest updatedData) {
        return ResponseEntity.ok(userService.updateUser(id, updatedData));
    }

    @PostMapping("/accounts")
    public ResponseEntity<String> deleteUser( @RequestBody List<Long> ids) {
        return ResponseEntity.ok(userService.deleteUser(ids));
    }

}
