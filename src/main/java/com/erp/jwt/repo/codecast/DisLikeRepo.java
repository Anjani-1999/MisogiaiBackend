package com.erp.jwt.repo.codecast;

import com.erp.jwt.entity.codecast.DisLikeEntity;
import com.erp.jwt.entity.codecast.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DisLikeRepo extends JpaRepository<DisLikeEntity,Long> {

    @Query(value = "SELECT * FROM dis_like_entity l WHERE l.video_id_fk = ?1 and l.user_id = ?2", nativeQuery = true)
    DisLikeEntity findDisLikeEntityByVideoIdAndUserId(Long videoId, Long userId);
}
