package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.CategoryResponse;
import com.ecommerce.kientv84.entites.CategoryEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse mapToCategoryResponse(CategoryEntity categoryEntity);
}
