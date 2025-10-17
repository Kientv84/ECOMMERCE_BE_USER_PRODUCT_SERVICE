package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.commons.EnumError;
import com.ecommerce.kientv84.dtos.request.CategoryRequest;
import com.ecommerce.kientv84.dtos.request.CategoryUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CategoryResponse;
import com.ecommerce.kientv84.entites.CategoryEntity;
import com.ecommerce.kientv84.exceptions.ServiceException;
import com.ecommerce.kientv84.mappers.CategoryMapper;
import com.ecommerce.kientv84.respositories.CategoryRepository;
import com.ecommerce.kientv84.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        try {
            List<CategoryResponse> listCategory = categoryRepository.findAll().stream().map(cate -> categoryMapper.mapToCategoryResponse(cate)).toList();

            return listCategory;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "category.get.error");
        }
    }

    @Override
    public CategoryResponse crateCategory(CategoryRequest categoryRequest) {
        try {
            CategoryEntity findCategory = categoryRepository.findCategoryByCategoryCode(categoryRequest.getCategoryCode());

            if ( findCategory != null ) {
                throw new ServiceException(EnumError.CATE_DATA_EXISTED, "category.exit");
            }

            CategoryEntity newCategory = CategoryEntity.builder()
                    .categoryName(categoryRequest.getCategoryName())
                    .categoryCode(categoryRequest.getCategoryCode())
                    .status(categoryRequest.getStatus())
                    .description(categoryRequest.getDescription())
                    .build();

            categoryRepository.save(newCategory);

            return categoryMapper.mapToCategoryResponse(newCategory);
        } catch (ServiceException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR);
        }
    }

    @Override
    public CategoryResponse getCategoryById(UUID id) {
        try {
            CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new ServiceException(EnumError.CATE_ERR_GET, "category.get.error"));

            return  categoryMapper.mapToCategoryResponse(category);
        } catch (ServiceException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public CategoryResponse updateCategoryById(UUID id, CategoryUpdateRequest updateData) {
        try {
            CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new ServiceException(EnumError.CATE_ERR_GET, "category.get.error"));

           if (updateData.getCategoryName() != null) {
               category.setCategoryName(updateData.getCategoryName());
           }
           if ( updateData.getCategoryCode() != null) {
               category.setCategoryCode(updateData.getCategoryCode());
           }
           if(updateData.getStatus() != null) {
               category.setStatus(updateData.getStatus());
           }

            categoryRepository.save(category);

            return  categoryMapper.mapToCategoryResponse(category);
        } catch (ServiceException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }

    @Override
    public String deleteCategories(List<UUID> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                throw new ServiceException(EnumError.CATE_ERR_DEL_EM, "category.delete.empty");
            }

            List<CategoryEntity> categories = categoryRepository.findAllById(ids);

            Set<UUID> foundIds = categories.stream().map(CategoryEntity::getId).collect(Collectors.toSet());

            List<UUID> notFoundIds = ids.stream().filter(id -> !foundIds.contains(id)).toList();

            if (!notFoundIds.isEmpty()) {
                throw new ServiceException(
                        EnumError.CATE_ERR_NOT_FOUND,
                        "category.delete.notfound " + notFoundIds,
                        new Object[]{notFoundIds.toString()}
                );
            }

            categoryRepository.deleteAllById(ids);

            log.info("Deleted categories successfully: {}", ids);

            return "Deleted categories successfully: {}" + ids;

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(EnumError.INTERNAL_ERROR, "sys.internal.error");
        }
    }
}
