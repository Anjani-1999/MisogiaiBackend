package com.erp.jwt.repo;

import com.erp.jwt.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEntity,Long> {
    Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);
}
