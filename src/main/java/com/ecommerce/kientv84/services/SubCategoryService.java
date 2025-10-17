package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.request.SubCategoryRequest;
import com.ecommerce.kientv84.dtos.request.SubCategoryUpdateRequest;
import com.ecommerce.kientv84.dtos.response.SubCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface SubCategoryService {
    List<SubCategoryResponse> getAllSubCategory();

    SubCategoryResponse createSubCategory(SubCategoryRequest subSubCategoryRequest);

    SubCategoryResponse getSubCategoryById(UUID id);

    SubCategoryResponse updateSubCategoryById(UUID id, SubCategoryUpdateRequest updateData);

    String deleteSubCategories(List<UUID> ids);
}
