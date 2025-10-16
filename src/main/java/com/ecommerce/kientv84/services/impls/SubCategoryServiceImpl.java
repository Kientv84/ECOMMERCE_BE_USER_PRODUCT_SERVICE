package com.ecommerce.kientv84.services.impls;

import com.ecommerce.kientv84.dtos.request.SubCategoryRequest;
import com.ecommerce.kientv84.dtos.request.SubCategoryUpdateRequest;
import com.ecommerce.kientv84.dtos.response.SubCategoryResponse;
import com.ecommerce.kientv84.services.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Override
    public List<SubCategoryResponse> getAllSubCategory() {
        return List.of();
    }

    @Override
    public SubCategoryResponse crateSubCategory(SubCategoryRequest subSubCategoryRequest) {
        return null;
    }

    @Override
    public SubCategoryResponse getSubCategoryById(UUID id) {
        return null;
    }

    @Override
    public SubCategoryResponse updateSubCategoryById(UUID id, SubCategoryUpdateRequest updateData) {
        return null;
    }

    @Override
    public String deleteSubCategories(List<UUID> ids) {
        return "";
    }
}
