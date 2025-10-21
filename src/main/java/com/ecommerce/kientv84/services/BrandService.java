package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.request.BrandRequest;
import com.ecommerce.kientv84.dtos.request.BrandUpdateRequest;
import com.ecommerce.kientv84.dtos.response.BrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<BrandResponse> getAllBrand();

    BrandResponse createBrand(BrandRequest brandRequest);

    BrandResponse getBrandById(UUID id);

    BrandResponse updateBrandById(UUID id, BrandUpdateRequest updateData);

    String deleteBrands(List<UUID> uuids);
}
