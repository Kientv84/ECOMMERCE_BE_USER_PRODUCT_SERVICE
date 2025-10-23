package com.ecommerce.kientv84.services;


import com.ecommerce.kientv84.dtos.request.CollectionRequest;
import com.ecommerce.kientv84.dtos.request.CollectionUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CollectionResponse;

import java.util.List;
import java.util.UUID;

public interface CollectionService {
    List<CollectionResponse> getAllCollection();
    CollectionResponse createCollection(CollectionRequest request);
    CollectionResponse getCollectionById(UUID uuid);
    CollectionResponse updateCollection(UUID uuid, CollectionUpdateRequest updateData);
    String deleteCollection(List<UUID> uuids);
}
