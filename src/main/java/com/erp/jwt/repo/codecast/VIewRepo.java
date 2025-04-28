package com.erp.jwt.repo.codecast;

import com.erp.jwt.entity.codecast.ViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VIewRepo extends JpaRepository<ViewEntity, Long> {

    @Query(value = "SELECT * FROM view_entity l WHERE l.video_id_fk = ?1 and l.user_id = ?2", nativeQuery = true)
    ViewEntity findLikeEntityByVideoIdAndUserId(Long videoId, Long userId);
}
