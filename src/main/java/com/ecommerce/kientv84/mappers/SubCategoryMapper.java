package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.request.SubCategoryRequest;
import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.dtos.response.SubCategoryResponse;
import com.ecommerce.kientv84.entites.ProductEntity;
import com.ecommerce.kientv84.entites.SubCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {
    SubCategoryResponse mapToSubCategoryResponse(SubCategoryEntity subCategoryEntity);
}
