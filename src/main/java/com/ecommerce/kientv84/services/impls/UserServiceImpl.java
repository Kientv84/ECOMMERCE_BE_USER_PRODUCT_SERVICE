package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.commons.StatusEnum;
import com.ecommerce.kientv84.commons.SuccessEnum;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.request.UserUpdateRequest;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.RoleEntity;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.dtos.response.ResponeResult;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.UserMapper;
import com.ecommerce.kientv84.respositories.RoleRepository;
import com.ecommerce.kientv84.respositories.UserRepository;
import com.ecommerce.kientv84.services.UserService;
import jakarta.websocket.EncodeException;
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
    private final RoleRepository roleRepository;

    @Override
    public List<UserResponse> getAllUser() {
        System.out.println("Get all user api calling ...");
        try {

            List<UserResponse> accounts = userRepository.findAll().stream().map(acc -> userMapper.mapToUserResponse(acc)).toList();

            return  accounts;

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.ACC_ERR_GET,"ACC-S-999");
        }
    }

    @Override
    public UserResponse createUser(UserRequest user) {
        try {
            // Tìm role trong DB
            RoleEntity role = roleRepository.findById(user.getRole())
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            if (userRepository.findByUserEmail(user.getEmail()) != null) {
                throw new ServiceException(EnumError.ACC_DATA_EXISTED, "user.email.existed");
            }

            String encodePassword = passwordEncoder.encode(user.getPassword()) ;

            UserEntity initUser = UserEntity.builder()
                    .userName(user.getName())
                    .role(role)
                    .status(StatusEnum.ACTIVE.getStatus())
                    .userPhoneNumber(user.getPhone())
                    .userEmail(user.getEmail())
                    .userPassword(encodePassword)
                    .build();

            UserEntity savedUser = userRepository.save(initUser);

            return userMapper.mapToUserResponse(savedUser);

        } catch (ServiceException e) {

            throw e;
        }
        catch (Exception e) {

            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999");
        }
    }


    @Override
    public UserResponse getById(Long id) {
        try {
           UserEntity user =  userRepository.findById(id)
                    .orElseThrow(() -> new ServiceException(EnumError.ACC_ERR_GET, "user.not.found", new Object[]{id}));

           return userMapper.mapToUserResponse(user);
        }
        catch (ServiceException e) {
            //các lỗi business (do bạn chủ động ném ra)
            throw e;
        }
        catch (Exception e) {
            // Bọc lại các lỗi hệ thống khác
            throw new ServiceException(EnumError.INTERNAL_ERROR);
        }
    }


    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest updatedData) {
        try {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new ServiceException(EnumError.ACC_ERR_GET, "user.not.found", new Object[]{id}));

            if (updatedData.getUserEmail() != null)
                user.setUserEmail(updatedData.getUserEmail());
            if (updatedData.getUserName() != null)
                user.setUserName(updatedData.getUserName());
            if (updatedData.getUserPhoneNumber() != null)
                user.setUserPhoneNumber(updatedData.getUserPhoneNumber());

            UserEntity saved = userRepository.save(user);
            return userMapper.mapToUserResponse(saved);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999", new Object[]{e.getMessage()});
        }
    }


    @Override
    public String deleteUser(List<Long> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                throw new ServiceException(EnumError.ACC_ERR_DEL_EM, "List ids to delete is empty!", new Object[]{});
            }

            userRepository.deleteAllById(ids);

            return "xoá người dùng thành công với id" + ids.toString();
            // hoặc ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999", new Object[]{e.getMessage()});
        }
    }

}
