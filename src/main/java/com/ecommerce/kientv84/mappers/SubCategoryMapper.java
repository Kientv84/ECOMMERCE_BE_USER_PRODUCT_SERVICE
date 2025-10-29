package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.SubCategoryResponse;
import com.ecommerce.kientv84.dtos.response.objectRes.SubCategoryObjectResponse;
import com.ecommerce.kientv84.entites.CategoryEntity;
import com.ecommerce.kientv84.entites.SubCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    SubCategoryResponse mapToSubCategoryResponse(SubCategoryEntity subCategoryEntity);

    SubCategoryObjectResponse mapToSubCategoryObjectResponse(CategoryEntity categoryEntity);
}
