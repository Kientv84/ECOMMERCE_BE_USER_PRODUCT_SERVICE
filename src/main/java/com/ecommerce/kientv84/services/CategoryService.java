package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.request.CategoryRequest;
import com.ecommerce.kientv84.dtos.request.CategoryUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();

    CategoryResponse crateCategory(CategoryRequest categoryRequest);

    CategoryResponse getCategoryById(UUID id);

    CategoryResponse updateCategoryById(UUID id, CategoryUpdateRequest updateData);

    String deleteCategories(List<UUID> ids);
}
