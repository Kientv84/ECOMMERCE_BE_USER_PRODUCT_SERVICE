package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.ProductEntity;
import com.ecommerce.kientv84.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
