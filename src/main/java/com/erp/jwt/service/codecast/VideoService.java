package com.erp.jwt.service.codecast;

import com.erp.jwt.request.codecast.BulkUploadVideoRequest;
import com.erp.jwt.request.codecast.CommentRequest;
import com.erp.jwt.request.codecast.UploadVideRequest;
import com.erp.jwt.request.codecast.VideoFilter;
import com.erp.jwt.response.BulkUploadVideResponse;
import com.erp.jwt.response.codecast.CommentResponse;
import com.erp.jwt.response.codecast.TagResponse;
import com.erp.jwt.response.codecast.UploadVideoResponse;
import com.erp.jwt.response.codecast.VideoFilterResponse;
import jakarta.validation.Valid;

public interface VideoService {

    UploadVideoResponse getVideoById(Long videoId);

    VideoFilterResponse getAllVideosByFilter(VideoFilter videoFilter);

    UploadVideoResponse uploadVideo( UploadVideRequest videoRequest);
    UploadVideoResponse editVideo(@Valid UploadVideRequest uploadVideRequest);
    BulkUploadVideResponse uploadVideosInBulk(BulkUploadVideoRequest videoRequest);


    Object getAllVideosByCategory(String category);

    Object getAllVideosByCategoryAndSubCategory(String category, String subCategory);

    Object getAllVideosByCategoryAndSubCategoryAndType(String category, String subCategory, String type);

    Object getAllVideosByCategoryAndType(String category, String type);

    Object getAllVideosByType(String type);

    Object getAllVideosBySubCategory(String subCategory);

    UploadVideoResponse likeVideo(@Valid UploadVideRequest uploadVideRequest);
    UploadVideoResponse dislikeVideo(@Valid UploadVideRequest uploadVideRequest);


    UploadVideoResponse commentVideo(@Valid CommentRequest commentRequest);

    BulkUploadVideResponse getAllVideosByUser(Long userId);

    UploadVideoResponse view(@Valid UploadVideRequest uploadVideRequest);

    TagResponse getTags();

    CommentResponse getCommentsByVideoId(Long videoId);

    Object deleteVideo(Long videoId);

}
