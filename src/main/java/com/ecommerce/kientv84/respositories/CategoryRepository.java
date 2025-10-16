package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    CategoryEntity findCategoryByCategoryCode(String categoryCode);
}
