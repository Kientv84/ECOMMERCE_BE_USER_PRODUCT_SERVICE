package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.BrandEntity;
import com.ecommerce.kientv84.entites.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
    BrandEntity findBrandByBrandCode(String brandCode);
}
