package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.BrandResponse;
import com.ecommerce.kientv84.entites.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse mapToBrandResponse(BrandEntity brandEntity);
}
