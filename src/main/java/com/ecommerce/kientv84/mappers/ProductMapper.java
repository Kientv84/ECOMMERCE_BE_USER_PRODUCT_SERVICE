package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.entites.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse mapToProductResponse(ProductEntity productEntity);
}
