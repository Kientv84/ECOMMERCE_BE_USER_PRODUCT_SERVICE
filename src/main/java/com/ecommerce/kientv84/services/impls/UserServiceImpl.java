package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.commons.SuccessEnum;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.dtos.response.ResponeResult;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public List<UserEntity> getAllUser() {
        return List.of();
    }

    @Override
    public UserResponse createUser(UserRequest user) {
        try {

            // Check trùng email
            if (userRepository.findByUserEmail(user.getEmail()) != null) {
                throw new ServiceException(EnumError.ACC_DATA_EXISTED, "user.email.existed");
            }

            // Tạo entity
            UserEntity initUser = UserEntity.builder()
                    .userName(user.getName())
                    .status("Đã kích hoạt")
                    .userPhoneNumber(user.getPhone())
                    .userEmail(user.getEmail())
                    .userPassword(user.getPassword())
                    .build();

            // Lưu DB
            UserEntity savedUser = userRepository.save(initUser);

            // Map sang response
            return userMapper.mapToUserEntity(savedUser);

        } catch (ServiceException e) {
            // ServiceException đã custom → để handler xử lý
            throw e;
        }
        catch (Exception e) {
            // Các lỗi ngoài dự kiến
            throw new ServiceException(EnumError.INTERNAL_ERROR, "D006", new Object[]{e.getMessage()});
        }
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
