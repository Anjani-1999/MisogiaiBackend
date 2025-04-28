package com.erp.jwt.repo;

import com.erp.jwt.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Long> {

    Optional<UserInfoEntity> findByEmailId(String emailId);
}
