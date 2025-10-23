package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.CategoryEntity;
import com.ecommerce.kientv84.entites.MaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaterialRepository extends JpaRepository<MaterialEntity, UUID> {
    MaterialEntity findMaterialByMaterialCode(String materialCode);
}
