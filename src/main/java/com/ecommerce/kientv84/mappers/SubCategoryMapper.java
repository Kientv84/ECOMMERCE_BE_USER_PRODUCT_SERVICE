package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.request.SubCategoryRequest;
import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.dtos.response.SubCategoryResponse;
import com.ecommerce.kientv84.entites.ProductEntity;
import com.ecommerce.kientv84.entites.SubCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {
    @Mapping(source = "category.id", target = "categoryId")
    SubCategoryResponse mapToSubCategoryResponse(SubCategoryEntity subCategoryEntity);
}
