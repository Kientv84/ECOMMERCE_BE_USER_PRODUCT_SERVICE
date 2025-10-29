package com.ecommerce.kientv84.mappers;

import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.dtos.response.objectRes.*;
import com.ecommerce.kientv84.entites.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse mapToProductResponse(ProductEntity productEntity);

    BrandObjectResponse mapToProductBrandResponse(BrandEntity brandEntity);

    CategoryObjectResponse mapToProductCategoryResponse(CategoryEntity categoryEntity);

    SubCategoryObjectResponse mapToProductSubCategoryResponse(SubCategoryEntity subCategoryEntity);

    CollectionObjectResponse mapToProductCollectionResponse(CollectionEntity collection);

    MaterialObjectResponse mapToProductMaterialResponse(MaterialEntity materialEntity);
}
