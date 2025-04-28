package com.erp.jwt.repo.codecast;

import com.erp.jwt.entity.codecast.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepo extends JpaRepository<VideoEntity, Long> {
}
