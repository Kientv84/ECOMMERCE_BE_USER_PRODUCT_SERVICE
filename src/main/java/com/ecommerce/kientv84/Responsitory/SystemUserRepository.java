package com.ecommerce.kientv84.Responsitory;

import com.ecommerce.kientv84.Entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {


    SystemUser findBySystemUserCode(String systemUserCode);

    SystemUser findBySystemUserEmail(String systemUserEmail);

    List<SystemUser> findBySystemRole_Id(Long roleId); //structure findBy + [Tên trường quan hệ trong Entity] + _ + [Tên trường trong Entity liên quan]
}
