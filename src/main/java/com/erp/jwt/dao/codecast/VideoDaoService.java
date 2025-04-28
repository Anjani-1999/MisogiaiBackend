package com.erp.jwt.dao.codecast;

import com.erp.jwt.entity.codecast.VideoEntity;
import com.erp.jwt.repo.codecast.VideoRepo;
import com.erp.jwt.request.codecast.VideoFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VideoDaoService {

    private final EntityManager primaryEntityManager;


    private final VideoRepo videoRepo;


    public VideoDaoService(EntityManager primaryEntityManager, VideoRepo videoRepo) {
        this.primaryEntityManager = primaryEntityManager;
        this.videoRepo = videoRepo;
    }

    public VideoEntity createVideo(VideoEntity videoEntity) {
        return videoRepo.save(videoEntity);
    }

    public Long getCountOfVideos(VideoFilter videoFilter) {
        try{
            primaryEntityManager.clear();

            CriteriaBuilder cb = primaryEntityManager.getCriteriaBuilder();
            // Create query for total count
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
            Root<VideoEntity> countRoot = countQuery.from(VideoEntity.class);
            List<Predicate> predicates = applyFilters(videoFilter, cb, countRoot);
            countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
            return primaryEntityManager.createQuery(countQuery).getSingleResult();

        }catch (Exception e){
            return 0l;
        }
    }

    private List<Predicate> applyFilters(VideoFilter videoFilter, CriteriaBuilder cb, Root<VideoEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (videoFilter.getUserId() != null) {
            predicates.add(root.get("createdBy").in(videoFilter.getUserId()));
        }
        if(videoFilter.getCategory()!=null){
            predicates.add(root.get("category").in(videoFilter.getCategory()));
        }
//            if(videoFilter.getTags()!=null){
//                predicates.add(root.get("tags").in(videoFilter.getTags()));
//            }
        if(videoFilter.getTags()!=null && !videoFilter.getTags().isEmpty()){
            predicates.add(root.get("tags").get("tag").in(videoFilter.getTags()));
        }
        if(videoFilter.getSearchBox()!=null){
            String searchPattern = "%" + videoFilter.getSearchBox().toLowerCase() + "%";
            Predicate searchPredicate = cb.like(cb.lower(root.get("title")), searchPattern);
            predicates.add(cb.or(searchPredicate));
        }
        return predicates;
    }

    public List<VideoEntity> getVideosByFilter(VideoFilter videoFilter) {
        try{
            primaryEntityManager.clear();

            CriteriaBuilder cb = primaryEntityManager.getCriteriaBuilder();
            // Create query for total count
            CriteriaQuery<VideoEntity> query = cb.createQuery(VideoEntity.class);
            Root<VideoEntity> root = query.from(VideoEntity.class);



            List<Predicate> predicates = applyFilters(videoFilter, cb, root);

            query.where(predicates.toArray(new Predicate[0]));
            query.orderBy(cb.desc(root.get("created")));


            List<VideoEntity> orderEntities = primaryEntityManager.createQuery(query).setHint("org.hibernate.cacheable", false).setFirstResult((int) ((videoFilter.getPage() - 1) * videoFilter.getPageSize())).setMaxResults(Math.toIntExact(videoFilter.getPageSize())).getResultList();
            if (ObjectUtils.isEmpty(orderEntities)) {
                log.info("could not find companies");
                return null;
            }

            return orderEntities;
        }catch (Exception e){
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public VideoEntity getVideoById(Long videoId) {
        try{
            primaryEntityManager.clear();
            return videoRepo.findById(videoId).orElse(null);
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public VideoEntity delelteVideo(Long videoId) {
        try{
            primaryEntityManager.clear();
            VideoEntity videoEntity = videoRepo.findById(videoId).orElse(null);
            if(videoEntity!=null){
                videoRepo.delete(videoEntity);
                return videoEntity;
            }
            return null;
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}

