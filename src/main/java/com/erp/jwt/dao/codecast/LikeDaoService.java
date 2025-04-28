package com.erp.jwt.dao.codecast;

import com.erp.jwt.entity.codecast.LikeEntity;
import com.erp.jwt.repo.codecast.LikeRepo;
import org.springframework.stereotype.Service;

@Service
public class LikeDaoService {

    private final LikeRepo likeRepo;

    public LikeDaoService(LikeRepo likeRepo) {
        this.likeRepo = likeRepo;
    }

    public LikeEntity getLikeByUserID(Long userId) {
        return likeRepo.findLikeEntityByUserId(userId);
    }

    public LikeEntity findLikeEntityByVideoIdAndUserId(Long videoId, Long userId) {
        return likeRepo.findLikeEntityByVideoIdAndUserId(videoId,userId );
    }
}
