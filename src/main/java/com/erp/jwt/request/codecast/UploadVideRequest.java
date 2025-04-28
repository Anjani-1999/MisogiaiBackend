package com.erp.jwt.request.codecast;

import com.erp.jwt.entity.codecast.CommentEntity;
import com.erp.jwt.entity.codecast.TagEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideRequest {


    private Long videoId;
    private Long userId;
    private String title;
    private String description;
    private String url;
    private String thumbnailUrl;
    private String duration;
    private String category;
    private List<TagRequest> tags;
    private Long likes;
    private Long views;
    private List<CommentRequest> comments;
    private Long numberOfComments;
}
