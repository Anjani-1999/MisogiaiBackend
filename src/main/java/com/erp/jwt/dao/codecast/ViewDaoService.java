package com.erp.jwt.dao.codecast;


import com.erp.jwt.entity.codecast.LikeEntity;
import com.erp.jwt.entity.codecast.ViewEntity;
import com.erp.jwt.repo.codecast.VIewRepo;
import org.springframework.stereotype.Service;

@Service
public class ViewDaoService {

    private final VIewRepo viewRepo;

    public ViewDaoService(VIewRepo viewRepo) {
        this.viewRepo = viewRepo;
    }

    public ViewEntity findViewEntityByVideoIdAndUserId(Long videoId, Long userId) {
        return viewRepo.findLikeEntityByVideoIdAndUserId(videoId,userId );
    }
}
