package com.ecommerce.kientv84.Responsitory;

import com.ecommerce.kientv84.Entity.SystemEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemEmployeeRepository extends JpaRepository<SystemEmployee, Long> {
}
