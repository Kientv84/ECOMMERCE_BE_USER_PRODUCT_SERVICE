package com.ecommerce.kientv84.Responsitory;

import com.ecommerce.kientv84.Entity.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
}
