package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.MaterialResponse;
import com.ecommerce.kientv84.entites.MaterialEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialResponse mapToMaterialResponse(MaterialEntity materialEntity);
}
