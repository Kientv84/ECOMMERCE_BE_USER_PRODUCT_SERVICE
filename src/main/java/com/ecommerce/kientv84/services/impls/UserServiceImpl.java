package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.Constant;
import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.commons.StatusEnum;
import com.ecommerce.kientv84.dtos.request.UserRequest;
import com.ecommerce.kientv84.dtos.request.UserUpdateRequest;
import com.ecommerce.kientv84.dtos.request.search.user.UserSearchModel;
import com.ecommerce.kientv84.dtos.request.search.user.UserSearchOption;
import com.ecommerce.kientv84.dtos.request.search.user.UserSearchRequest;
import com.ecommerce.kientv84.dtos.response.PagedResponse;
import com.ecommerce.kientv84.dtos.response.UserResponse;
import com.ecommerce.kientv84.entites.RoleEntity;
import com.ecommerce.kientv84.entites.UserEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.UserMapper;
import com.ecommerce.kientv84.respositories.RoleRepository;
import com.ecommerce.kientv84.respositories.UserRepository;
import com.ecommerce.kientv84.services.RedisService;
import com.ecommerce.kientv84.services.UserService;
import com.ecommerce.kientv84.utils.PageableUtils;
import com.ecommerce.kientv84.utils.SpecificationBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RedisService redisService;

    @Override
    public PagedResponse<UserResponse> searchUsers(UserSearchRequest req) {
        log.info("Get all user api calling ...");
        String key = "user:list:" + req.hashKey();
        try {

            // 1. Check cache
            PagedResponse<UserResponse> cached =
                    redisService.getValue(key, new TypeReference<PagedResponse<UserResponse>>() {});

            if (cached != null) {
                log.info("Redis read for key {}", key);
                return cached;
            }

            //
            UserSearchOption option = req.getSearchOption();
            UserSearchModel model = req.getSearchModel();

            List<String> allowedFields = List.of("userName", "createdDate");

            PageRequest pageRequest = PageableUtils.buildPageRequest(
                    option.getPage(),
                    option.getSize(),
                    option.getSort(),
                    allowedFields,
                    "createdDate",
                    Sort.Direction.DESC
            );

            Specification<UserEntity> spec = new SpecificationBuilder<UserEntity>()
                    .equal("status", model.getStatus())
                    .equal("role.id", model.getRoleId())
                    .likeAnyFieldIgnoreCase(model.getQ(), "userName", "userEmail")
                    .build();

            Page<UserResponse> result = userRepository.findAll(spec, pageRequest)
                    .map(userMapper::mapToUserResponse);

            PagedResponse<UserResponse> response = new PagedResponse<>(
                    result.getNumber(),
                    result.getSize(),
                    result.getTotalElements(),
                    result.getTotalPages(),
                    result.getContent()
            );

            // 3. query list nên TTL ngắn
            redisService.setValue(key, response, Constant.SEARCH_CACHE_TTL);

            log.info("Redis MISS, caching search result for key {}", key);

            return response;

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

            redisService.deleteByKeys("user:list:*");

            return userMapper.mapToUserResponse(savedUser);

        } catch (ServiceException e) {

            throw e;
        } catch (Exception e) {

            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999");
        }
    }


    @Override
    public UserResponse getById(UUID id) {
        log.info("Calling get by id api with user {}", id);

        String key = "user:"+id;

        try {
            UserResponse cached = redisService.getValue(key, UserResponse.class);
            if (cached != null) {
                log.info("Redis get for key: {}", key);
                return cached;
            }

            UserEntity user = userRepository.findById(id).orElseThrow(() -> new ServiceException(EnumError.ACC_ERR_GET, "user.not.found", new Object[]{id}));

            UserResponse response = userMapper.mapToUserResponse(user);

            redisService.setValue(key, response, Constant.CACHE_TTL);

            return response;

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while getting user by id {}", id, e);
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

            // Invalidate cache
            String key = "user:" + id;
            redisService.deleteByKey(key);

            redisService.deleteByKeys("user:" + id, "user:list:*");

            log.info("Cache invalidated for key {}", key);

            return userMapper.mapToUserResponse(saved);

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while updating user {}", id, e);
            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999", new Object[]{e.getMessage()});
        }
    }

    @Override
    public List<UserResponse> searchUserSuggestion(String q, int limit) {
        List<UserEntity> users = userRepository.searchUserSuggestion(q, limit);
        return users.stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public String deleteUser(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new ServiceException(EnumError.ACC_ERR_DEL_EM, "List ids to delete is empty!");
        }

        // Lấy tất cả user tồn tại
        List<UserEntity> users = userRepository.findAllById(ids);
        List<UUID> notFoundIds = ids.stream()
                .filter(id -> users.stream().noneMatch(u -> u.getId().equals(id)))
                .toList();
        if (!notFoundIds.isEmpty()) {
            throw new ServiceException(EnumError.ACC_ERR_NOT_FOUND,
                    "user.delete.notfound " + notFoundIds,
                    new Object[]{notFoundIds.toString()});
        }

        // Soft delete: update status
        users.forEach(u -> u.setStatus(StatusEnum.DELETED.getStatus()));
        userRepository.saveAll(users);

        // Xóa cache
        ids.forEach(id -> redisService.deleteByKey("user:" + id));
        redisService.deleteByKeys("user:list:*");

        log.info("Deleted users successfully and cache invalidated: {}", ids);
        return "Deleted users successfully: " + ids;
    }

    @Override
    public String hardDeleteUser(List<UUID> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                throw new ServiceException(EnumError.ACC_ERR_DEL_EM, "List ids to delete is empty!");
            }

            List<UserEntity> users = userRepository.findAllById(ids);

            List<UUID> notFoundIds = ids.stream()
                    .filter(id -> users.stream().noneMatch(u -> u.getId().equals(id)))
                    .toList();
            if (!notFoundIds.isEmpty()) {
                throw new ServiceException(EnumError.ACC_ERR_NOT_FOUND,
                        "Users not found: " + notFoundIds,
                        new Object[]{notFoundIds.toString()});
            }

            userRepository.deleteAll(users);

            ids.forEach(id -> redisService.deleteByKey("user:" + id));
            redisService.deleteByKeys("user:list:*");

            log.info("Hard deleted users {} and invalidated cache", ids);

            // Feature: publish event cho các microservice khác

            return "Deleted users successfully: " + ids;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while hard deleting users {}", ids, e);
            throw new ServiceException(EnumError.INTERNAL_ERROR, "ACC-S-999", new Object[]{e.getMessage()});
        }
    }
}

//user:{id}
//user:list:{page}:{size}:{q}:{sort}
//auth:token:{userId}
//order:{id}
//inventory:product:{id}
