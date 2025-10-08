package com.ecommerce.kientv84.respositories;

import com.ecommerce.kientv84.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


//    UserEntity findByUserCode(String code);
//
    UserEntity findByUserEmail(String email);
//
//    List<UserEntity> findByRole_Id(Long id); //structure findBy + [Tên trường quan hệ trong Entity] + _ + [Tên trường trong Entity liên quan]
}
