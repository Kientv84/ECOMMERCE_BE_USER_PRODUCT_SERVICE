package com.ecommerce.kientv84.Responsitory;

import com.ecommerce.kientv84.Entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {


    SystemUser findBySystemUserCode(String systemUserCode);
    SystemUser findBySystemUserEmail(String systemUserEmail);
}
