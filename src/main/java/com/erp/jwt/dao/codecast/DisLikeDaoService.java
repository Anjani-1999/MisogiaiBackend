package com.erp.jwt.dao.codecast;

import com.erp.jwt.entity.codecast.DisLikeEntity;
import com.erp.jwt.entity.codecast.LikeEntity;
import com.erp.jwt.repo.codecast.DisLikeRepo;
import com.erp.jwt.repo.codecast.LikeRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class DisLikeDaoService  {
    private final DisLikeRepo disLikeRepo;

    public DisLikeDaoService( DisLikeRepo disLikeRepo) {
        this.disLikeRepo = disLikeRepo;
    }


    public DisLikeEntity findDisLikeEntityByVideoIdAndUserId(Long videoId, Long userId) {
        return disLikeRepo.findDisLikeEntityByVideoIdAndUserId(videoId,userId );
    }
}
