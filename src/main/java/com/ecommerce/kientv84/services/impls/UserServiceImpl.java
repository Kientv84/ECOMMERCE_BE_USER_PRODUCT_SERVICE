package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.commons.StatusEnum;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.request.UserUpdateRequest;
import com.ecommerce.kientv84.dtos.request.search.UserSearchRequest;
import com.ecommerce.kientv84.dtos.response.PagedResponse;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.RoleEntity;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.UserMapper;
import com.ecommerce.kientv84.respositories.RoleRepository;
import com.ecommerce.kientv84.respositories.UserRepository;
import com.ecommerce.kientv84.services.UserService;
import com.ecommerce.kientv84.utils.PageableUtils;
import com.ecommerce.kientv84.utils.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public PagedResponse<UserResponse> searchUsers(UserSearchRequest req) {
        log.info("Get all user api calling ...");

        try {
            List<String> allowedFields = List.of("userName", "createdDate");

            PageRequest pageRequest = PageableUtils.buildPageRequest(
                    req.getPage(),
                    req.getSize(),
                    req.getSort(),
                    allowedFields,
                    "createdDate",
                    Sort.Direction.DESC
            );

            Specification<UserEntity> spec = new SpecificationBuilder<UserEntity>()
                    .equal("status", req.getStatus())
                    .equal("role.id", req.getRoleId())
                    .likeIgnoreCase("userName", req.getQ())
                    .likeIgnoreCase("userEmail", req.getQ())
                    .build();


            Page<UserResponse> result = userRepository.findAll(spec, pageRequest)
                    .map(userMapper::mapToUserResponse);

            return new PagedResponse<>(
                    result.getNumber(),
                    result.getSize(),
                    result.getTotalElements(),
                    result.getTotalPages(),
                    result.getContent()
            );

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.ACC_ERR_GET, "ACC-S-999", new Object[]{e.getMessage()});
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

            String encodePassword = passwordEncoder.encode(user.getPassword());

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
        } catch (Exception e) {

            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999");
        }
    }


    @Override
    public UserResponse getById(UUID id) {
        try {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new ServiceException(EnumError.ACC_ERR_GET, "user.not.found", new Object[]{id}));

            return userMapper.mapToUserResponse(user);
        } catch (ServiceException e) {
            //các lỗi business (do bạn chủ động ném ra)
            throw e;
        } catch (Exception e) {
            // Bọc lại các lỗi hệ thống khác
            throw new ServiceException(EnumError.INTERNAL_ERROR);
        }
    }


    @Override
    public UserResponse updateUser(UUID id, UserUpdateRequest updatedData) {
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
    public String deleteUser(List<UUID> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                throw new ServiceException(
                        EnumError.ACC_ERR_DEL_EM,
                        "List ids to delete is empty!",
                        new Object[]{}
                );
            }

            List<UserEntity> users = userRepository.findAllById(ids);
            Set<UUID> foundIds = users.stream()
                    .map(UserEntity::getId)
                    .collect(Collectors.toSet());

            List<UUID> notFoundIds = ids.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();

            if (!notFoundIds.isEmpty()) {
                throw new ServiceException(
                        EnumError.ACC_ERR_NOT_FOUND,
                        "user.delete.notfound " + notFoundIds,
                        new Object[]{notFoundIds.toString()}
                );
            }

            userRepository.deleteAllById(ids);
            log.info("Deleted users successfully: {}", ids);

            return "Deleted users successfully: {}" + ids;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(
                    EnumError.INTERNAL_ERROR,
                    "ACC-S-999",
                    new Object[]{e.getMessage()}
            );
        }
    }


}
