package com.ecommerce.kientv84.controller;

import com.ecommerce.kientv84.dtos.request.CollectionRequest;
import com.ecommerce.kientv84.dtos.request.CollectionUpdateRequest;
import com.ecommerce.kientv84.dtos.response.CollectionResponse;
import com.ecommerce.kientv84.services.CollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping("/collections")
    public ResponseEntity<List<CollectionResponse>> getAllCollection() {
        return ResponseEntity.ok(collectionService.getAllCollection());
    }

    @PostMapping("/collection")
    public ResponseEntity<CollectionResponse> createCollection(@Valid @RequestBody CollectionRequest request) {
        return ResponseEntity.ok(collectionService.createCollection(request));
    }

    @GetMapping("/collection/{uuid}")
    public ResponseEntity<CollectionResponse> getCollectionById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(collectionService.getCollectionById(uuid));
    }

    @PostMapping("/collection/{uuid}")
    public ResponseEntity<CollectionResponse> updateCollectionById(@PathVariable UUID uuid, @RequestBody CollectionUpdateRequest updateData) {
        return ResponseEntity.ok(collectionService.updateCollection(uuid, updateData));
    }

    @PostMapping("/collections")
    public String deleteCollection(@RequestBody List<UUID> uuids) {
        return collectionService.deleteCollection(uuids);
    }
}
