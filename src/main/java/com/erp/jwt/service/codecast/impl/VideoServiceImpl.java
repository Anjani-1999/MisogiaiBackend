package com.erp.jwt.service.codecast.impl;

import com.erp.jwt.dao.codecast.*;
import com.erp.jwt.entity.codecast.*;
import com.erp.jwt.mapper.GlobalMapper;
import com.erp.jwt.request.codecast.*;
import com.erp.jwt.response.BulkUploadVideResponse;
import com.erp.jwt.response.codecast.CommentResponse;
import com.erp.jwt.response.codecast.TagResponse;
import com.erp.jwt.response.codecast.UploadVideoResponse;
import com.erp.jwt.response.codecast.VideoFilterResponse;
import com.erp.jwt.service.codecast.VideoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoDaoService videoDaoService;
    private final GlobalMapper globalMapper;
    private final LikeDaoService likeDaoService;
    private final ViewDaoService viewDaoService;
    private final TagDaoService tagDaoService;
    private final CommentDaoService commentDaoService;

    public VideoServiceImpl(VideoDaoService videoDaoService, GlobalMapper globalMapper, LikeDaoService likeDaoService, ViewDaoService viewDaoService, TagDaoService tagDaoService, CommentDaoService commentDaoService) {
        this.videoDaoService = videoDaoService;
        this.globalMapper = globalMapper;
        this.likeDaoService = likeDaoService;
        this.viewDaoService = viewDaoService;
        this.tagDaoService = tagDaoService;
        this.commentDaoService = commentDaoService;
    }

    @Override
    public UploadVideoResponse getVideoById(Long videoId) {
        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();
        try{
            VideoEntity videoEntity = videoDaoService.getVideoById(videoId);
            if(videoEntity==null){
                uploadVideoResponse.setMessage("No video found with this id");
                uploadVideoResponse.setStatus(404);
                return uploadVideoResponse;
            }
            UploadVideRequest uploadVideRequest = globalMapper.entityToDto(videoEntity, UploadVideRequest.class);
            uploadVideoResponse.setData(uploadVideRequest);
            uploadVideoResponse.setMessage("Video fetched successfully");
            uploadVideoResponse.setStatus(200);
            return uploadVideoResponse;
        }catch (Exception e){
            log.error("Error while getting video by id: {}", e.getMessage());
            uploadVideoResponse.setMessage(e.getMessage());
            uploadVideoResponse.setStatus(500);
            return uploadVideoResponse;
        }
    }

    @Override
    public VideoFilterResponse getAllVideosByFilter(VideoFilter videoFilter) {

        VideoFilterResponse videoFilterResponse = new VideoFilterResponse();
        try{
            final int DEFAULT_PAGE_SIZE = 10;
            final int DEFAULT_PAGE_NUMBER = 1;

            if (ObjectUtils.isEmpty(videoFilter.getPage()))
                videoFilter.setPage((long) DEFAULT_PAGE_NUMBER);
            if (ObjectUtils.isEmpty(videoFilter.getPageSize()) || videoFilter.getPageSize() > 100)
                videoFilter.setPageSize((long) DEFAULT_PAGE_SIZE);
            if (videoFilter.getPage() < 1) videoFilter.setPage((long) DEFAULT_PAGE_NUMBER);

            Long count = videoDaoService.getCountOfVideos(videoFilter);

            if (count <= 0 ){
                log.info("there are no company present");
                return videoFilterResponse;
            }

            List<VideoEntity> orderEntities = videoDaoService.getVideosByFilter(videoFilter);
            if(orderEntities==null || orderEntities.isEmpty()){
                return videoFilterResponse;
            }
            List<UploadVideRequest> videoList = globalMapper.entityToDto(orderEntities, UploadVideRequest.class);
            videoFilterResponse.setData(videoList);
            videoFilterResponse.setMessage("Videos fetched successfully");
            videoFilterResponse.setStatus(200);
            videoFilterResponse.setTotalResults(count);
            videoFilterResponse.setPage(videoFilter.getPage());
            videoFilterResponse.setPageSize(videoFilter.getPageSize());
            videoFilterResponse.setTotalPages((long) Math.ceil((double) count / videoFilter.getPageSize()));
            return videoFilterResponse;

        }catch (Exception e){
            log.error("Error while getting videos by filter: {}", e.getMessage());
            videoFilterResponse.setMessage(e.getMessage());
            videoFilterResponse.setStatus(500);
            return videoFilterResponse;
        }
    }

    @Override
    public UploadVideoResponse uploadVideo(UploadVideRequest videoRequest) {
        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();
        try{
            VideoEntity videoEntity = globalMapper.dtoToEntity(videoRequest, VideoEntity.class);
            videoEntity.setCreatedBy(videoRequest.getUserId());
            videoEntity.getTags().forEach(tagEntity -> tagEntity.setVideoEntity(videoEntity));
            VideoEntity savedVideo = videoDaoService.createVideo(videoEntity);
            UploadVideRequest uploadVideRequest = globalMapper.entityToDto(savedVideo, UploadVideRequest.class);
            uploadVideoResponse.setData(uploadVideRequest);
            uploadVideoResponse.setMessage("Video uploaded successfully");
            uploadVideoResponse.setStatus(200);
            return uploadVideoResponse;

        }catch (Exception e){
            uploadVideoResponse.setMessage(e.getMessage());
            uploadVideoResponse.setStatus(500);
            return uploadVideoResponse;
        }
    }

    @Override
    public UploadVideoResponse editVideo(UploadVideRequest uploadVideRequest) {
        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();
        try{
            VideoEntity videoEntity = videoDaoService.getVideoById(uploadVideRequest.getVideoId());
            if(videoEntity==null){
                uploadVideoResponse.setMessage("No video found with this id");
                uploadVideoResponse.setStatus(404);
                return uploadVideoResponse;
            }
            videoEntity.setTitle(uploadVideRequest.getTitle()!=null?uploadVideRequest.getTitle():videoEntity.getTitle());
            videoEntity.setDescription(uploadVideRequest.getDescription()!=null?uploadVideRequest.getDescription():videoEntity.getDescription());
            videoEntity.setUpdatedBy(uploadVideRequest.getUserId().toString());
            uploadVideRequest.getTags().forEach(tagRequest -> {
                if(tagRequest.getTagId()!=null){
                    videoEntity.getTags()
                            .stream()
                            .filter(tagEntity -> tagEntity.getTagId().equals(tagRequest.getTagId()))
                            .forEach(tagEntity -> {
                                tagEntity.setTag(tagRequest.getTag()!=null?tagRequest.getTag():tagEntity.getTag());
                            });
                }else{
                    TagEntity tagEntity = new TagEntity();
                    tagEntity.setTag(tagRequest.getTag());
                    tagEntity.setVideoEntity(videoEntity);
                    videoEntity.getTags().add(tagEntity);
                }
            });
            VideoEntity savedVideo = videoDaoService.createVideo(videoEntity);
            UploadVideRequest uploadVideRequest1 = globalMapper.entityToDto(savedVideo, UploadVideRequest.class);
            uploadVideoResponse.setData(uploadVideRequest1);
            uploadVideoResponse.setMessage("Video uploaded successfully");
            uploadVideoResponse.setStatus(200);
            return uploadVideoResponse;

        }catch (Exception e){
            uploadVideoResponse.setMessage(e.getMessage());
            uploadVideoResponse.setStatus(500);
            return uploadVideoResponse;
        }
    }

    @Override
    public BulkUploadVideResponse uploadVideosInBulk(BulkUploadVideoRequest videoRequest) {
        BulkUploadVideResponse bulkUploadVideResponse = new BulkUploadVideResponse();
        BulkUploadVideoRequest bulkUploadVideoRequest = new BulkUploadVideoRequest();
        List<UploadVideRequest> uploadVideRequestList = new ArrayList<>(); // Create a new list

        try {
            for (UploadVideRequest videoEntity : videoRequest.getVideos()) {
                UploadVideoResponse uploadVideoResponse = uploadVideo(videoEntity);
                uploadVideRequestList.add(uploadVideoResponse.getData()); // Add results to a new list
            }

            bulkUploadVideoRequest.setVideos(uploadVideRequestList);
            bulkUploadVideResponse.setData(bulkUploadVideoRequest);
            bulkUploadVideResponse.setMessage("Videos uploaded successfully");
            bulkUploadVideResponse.setStatus(200);
            return bulkUploadVideResponse;

        } catch (Exception e) {
            log.error("Error while uploading videos in bulk: {}", e.getMessage());
            bulkUploadVideResponse.setMessage(e.getMessage());
            bulkUploadVideResponse.setStatus(500);
            return bulkUploadVideResponse;
        }
    }

    @Override
    public Object getAllVideosByCategory(String category) {
        return null;
    }

    @Override
    public Object getAllVideosByCategoryAndSubCategory(String category, String subCategory) {
        return null;
    }

    @Override
    public Object getAllVideosByCategoryAndSubCategoryAndType(String category, String subCategory, String type) {
        return null;
    }

    @Override
    public Object getAllVideosByCategoryAndType(String category, String type) {
        return null;
    }

    @Override
    public Object getAllVideosByType(String type) {
        return null;
    }

    @Override
    public Object getAllVideosBySubCategory(String subCategory) {
        return null;
    }

    @Override
    public UploadVideoResponse likeVideo(UploadVideRequest uploadVideRequest) {
        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();


        try {
            VideoEntity videoEntity = videoDaoService.getVideoById(uploadVideRequest.getVideoId());
            if (videoEntity == null) {
                uploadVideoResponse.setMessage("No video found with this id");
                uploadVideoResponse.setStatus(404);
                return uploadVideoResponse;
            }
            LikeEntity likeEntity = likeDaoService.findLikeEntityByVideoIdAndUserId(uploadVideRequest.getVideoId(),uploadVideRequest.getUserId());
            if (likeEntity == null) {
                likeEntity = new LikeEntity();
                likeEntity.setCreatedBy(uploadVideRequest.getUserId());
                likeEntity.setUserId(uploadVideRequest.getUserId());
                likeEntity.setVideoEntity(videoEntity);
                videoEntity.getLikeEntities().add(likeEntity);
                videoEntity.setLikes((long) videoEntity.getLikeEntities().size());
            } else {
                videoEntity.getLikeEntities().remove(likeEntity);
                videoEntity.setLikes((long) videoEntity.getLikeEntities().size());
            }
            videoDaoService.createVideo(videoEntity);
            uploadVideoResponse.setData(globalMapper.entityToDto(videoEntity, UploadVideRequest.class));
            uploadVideoResponse.setMessage("Video liked successfully");
            uploadVideoResponse.setStatus(200);
            return uploadVideoResponse;
        }catch (Exception e){
            log.error("Error while liking video: {}", e.getMessage());
            uploadVideoResponse.setMessage(e.getMessage());
            uploadVideoResponse.setStatus(500);
            return uploadVideoResponse;
        }
    }

    @Override
    public UploadVideoResponse view(UploadVideRequest uploadVideRequest) {
        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();


        try {
            VideoEntity videoEntity = videoDaoService.getVideoById(uploadVideRequest.getVideoId());
            if (videoEntity == null) {
                uploadVideoResponse.setMessage("No video found with this id");
                uploadVideoResponse.setStatus(404);
                return uploadVideoResponse;
            }
            ViewEntity viewEntity = viewDaoService.findViewEntityByVideoIdAndUserId(uploadVideRequest.getVideoId(),uploadVideRequest.getUserId());
            if (viewEntity == null) {
                viewEntity = new ViewEntity();
                viewEntity.setCreatedBy(uploadVideRequest.getUserId());
                viewEntity.setUserId(uploadVideRequest.getUserId());
                viewEntity.setVideoEntity(videoEntity);
                videoEntity.getViewEntities().add(viewEntity);
                videoEntity.setViews((long) videoEntity.getViewEntities().size());
                videoDaoService.createVideo(videoEntity);
            }
            uploadVideoResponse.setData(globalMapper.entityToDto(videoEntity, UploadVideRequest.class));
            uploadVideoResponse.setMessage("Video viewed successfully");
            uploadVideoResponse.setStatus(200);
            return uploadVideoResponse;
        }catch (Exception e){
            log.error("Error while liking video: {}", e.getMessage());
            uploadVideoResponse.setMessage(e.getMessage());
            uploadVideoResponse.setStatus(500);
            return uploadVideoResponse;
        }
    }

    @Override
    public TagResponse getTags() {
        TagResponse tagResponse = new TagResponse();

        try {
            List<String> tagEntities = tagDaoService.getUniqueTags();
            List<TagEntity> tags = new ArrayList<>();
            tagEntities.forEach(tag ->{
                TagEntity tagEntity = new TagEntity();
                tagEntity.setTag(tag);
                tags.add(tagEntity);
            });
            if (tags.isEmpty()) {
                tagResponse.setMessage("No tags found");
                tagResponse.setStatus(404);
                return tagResponse;
            }
            List<TagRequest> tagRequests = globalMapper.entityToDto(tags, TagRequest.class);
            tagResponse.setTags(tagRequests);
            tagResponse.setMessage("Tags fetched successfully");
            tagResponse.setStatus(200);
            return tagResponse;
        }catch (Exception e){
            log.error("Error while fetching tags: {}", e.getMessage());
            tagResponse.setMessage(e.getMessage());
            tagResponse.setStatus(500);
            return tagResponse;
        }
    }

    @Override
    public CommentResponse getCommentsByVideoId(Long videoId) {
        CommentResponse commentResponse = new CommentResponse();

        try{
            List<CommentEntity> commentEntities = commentDaoService.getAllCommentsByVideoId(videoId);
            if (commentEntities.isEmpty()) {
                commentResponse.setMessage("No comments found");
                commentResponse.setStatus(404);
                return commentResponse;
            }
            List<CommentRequest> commentRequests = globalMapper.entityToDto(commentEntities, CommentRequest.class);
            commentResponse.setComments(commentRequests);
            commentResponse.setMessage("Comments fetched successfully");
            commentResponse.setStatus(200);
            return commentResponse;
        }catch (Exception e){
            log.error("Error while fetching comments: {}", e.getMessage());
            commentResponse.setMessage(e.getMessage());
            commentResponse.setStatus(500);
            return commentResponse;
        }
    }

    @Override
    public Object deleteVideo(Long videoId) {
        try{
            VideoEntity videoEntity = videoDaoService.delelteVideo(videoId);
            return videoEntity;
        }catch (Exception e){
            log.error("Error while deleting video: {}", e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public UploadVideoResponse commentVideo(@Valid CommentRequest commentRequest) {
        UploadVideoResponse uploadVideoResponse = new UploadVideoResponse();

        try {
            VideoEntity videoEntity = videoDaoService.getVideoById(commentRequest.getVideoId());
            if (videoEntity == null) {
                uploadVideoResponse.setMessage("No video found with this id");
                uploadVideoResponse.setStatus(404);
                return uploadVideoResponse;
            }

            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setComment(commentRequest.getComment());
            commentEntity.setCreatedBy(commentRequest.getUserId());
            commentEntity.setVideoEntity(videoEntity);
            commentEntity.setUserId(commentRequest.getUserId());
            videoEntity.getComments().add(commentEntity);
            videoEntity.setNumberOfComments((long) videoEntity.getComments().size());
            videoDaoService.createVideo(videoEntity);

            uploadVideoResponse.setData(globalMapper.entityToDto(videoEntity, UploadVideRequest.class));
            uploadVideoResponse.setMessage("Video liked successfully");
            uploadVideoResponse.setStatus(200);
            return uploadVideoResponse;
        }catch (Exception e){
            log.error("Error while commenting video: {}", e.getMessage());
            uploadVideoResponse.setMessage(e.getMessage());
            uploadVideoResponse.setStatus(500);
            return uploadVideoResponse;
        }
    }

    @Override
    public BulkUploadVideResponse getAllVideosByUser(Long userId) {
        return null;
    }


}
