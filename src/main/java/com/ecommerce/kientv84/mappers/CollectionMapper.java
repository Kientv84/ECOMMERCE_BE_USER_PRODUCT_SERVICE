package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.CollectionResponse;
import com.ecommerce.kientv84.entites.CollectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    CollectionResponse mapToCollectionResponse(CollectionEntity collectionEntity);
}
