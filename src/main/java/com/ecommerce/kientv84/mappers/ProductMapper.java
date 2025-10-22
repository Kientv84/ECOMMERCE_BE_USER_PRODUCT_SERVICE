package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.entites.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "brand.id", target = "brand")
    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "subCategory.id", target = "subCategory")
    ProductResponse mapToProductResponse(ProductEntity productEntity);
}
