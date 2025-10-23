package com.ecommerce.kientv84.services;

import com.ecommerce.kientv84.dtos.request.MaterialRequest;
import com.ecommerce.kientv84.dtos.request.MaterialUpdateRequest;
import com.ecommerce.kientv84.dtos.response.MaterialResponse;

import java.util.List;
import java.util.UUID;

public interface MaterialService {
    List<MaterialResponse> getAllMaterial();
    MaterialResponse createMaterial(MaterialRequest request);
    MaterialResponse getMaterialById(UUID uuid);
    MaterialResponse updateMaterial(UUID uuid, MaterialUpdateRequest updateData);
    String deleteMaterial(List<UUID> uuids);
}
