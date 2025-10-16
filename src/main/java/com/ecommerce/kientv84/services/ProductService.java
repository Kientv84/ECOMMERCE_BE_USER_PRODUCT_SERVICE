package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.request.ProductRequest;
import com.ecommerce.kientv84.dtos.request.ProductUpdateRequest;
import com.ecommerce.kientv84.dtos.response.ProductResponse;
import com.ecommerce.kientv84.entites.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductResponse> getAllProduct();

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(UUID uuid);

    ProductResponse updateProductById(UUID uuid, ProductUpdateRequest updateData);

    String deleteProduct(List<UUID> uuids);

    //Sub function

    String generateNameProduct(ProductEntity productEntity);
}
