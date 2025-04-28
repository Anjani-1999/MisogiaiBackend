package com.erp.jwt.repo.codecast;

import com.erp.jwt.entity.codecast.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {

    @Query("From CommentEntity c where c.videoEntity.videoId = ?1")
    List<CommentEntity> findAllByVideoId(Long videoId);
}
