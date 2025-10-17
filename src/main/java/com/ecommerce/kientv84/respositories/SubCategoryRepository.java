package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, UUID> {

    SubCategoryEntity findSubCategoryBySubCategoryCode(String code);
}
