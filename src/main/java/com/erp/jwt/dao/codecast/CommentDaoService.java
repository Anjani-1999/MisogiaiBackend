package com.erp.jwt.dao.codecast;

import com.erp.jwt.entity.codecast.CommentEntity;
import com.erp.jwt.repo.codecast.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDaoService {

    private final CommentRepo commentRepo;

    public CommentDaoService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public CommentEntity createComment(CommentEntity comment) {
        return commentRepo.save(comment);
    }

    public List<CommentEntity> getAllCommentsByVideoId(Long videoId) {
        return commentRepo.findAllByVideoId(videoId);
    }
}
