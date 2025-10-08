package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.dtos.response.ResponeResult;
import com.ecommerce.kientv84.respositories.UserRepository;
import com.ecommerce.kientv84.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUser() {
        return List.of();
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity getById(Long id) {
        return null;
    }

    @Override
    public UserEntity getByCode(String code) {
        return null;
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedData) {
        return null;
    }

    @Override
    public Boolean deleteUser(List<Long> ids) {
        return null;
    }

    @Override
    public List<UserEntity> getAllByRole(Long roleId) {
        return List.of();
    }

    @Override
    public String generateCode() {
        return "";
    }
}
