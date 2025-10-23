package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectionRepository extends JpaRepository<CollectionEntity, UUID> {
    CollectionEntity findCollectionByCollectionCode(String collectionCode);
}
