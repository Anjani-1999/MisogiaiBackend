package com.erp.jwt.repo.codecast;

import com.erp.jwt.entity.codecast.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {

    LikeEntity findLikeEntityByUserId(Long userId);

    @Query(value = "SELECT * FROM like_entity l WHERE l.video_id_fk = ?1 and l.user_id = ?2", nativeQuery = true)
    LikeEntity findLikeEntityByVideoIdAndUserId(Long videoId,Long userId);

    @Query("From LikeEntity l where l.userId = ?1 and l.commentEntity.commentId = ?2")
    LikeEntity findLikeEntityByCommentIdAndUserId(Long userId,Long commentId);

}
