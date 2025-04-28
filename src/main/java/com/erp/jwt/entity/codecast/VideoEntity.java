package com.erp.jwt.entity.codecast;

import com.erp.jwt.entity.BaseEntity;
import com.erp.jwt.entity.UserInfoEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="VIDEO")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "videoId"
)
public class VideoEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long videoId;
    private String title;
    private String description;
    private String url;
    private String thumbnailUrl;
    private String duration;
    private String category;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "videoEntity")
    private List<TagEntity> tags;

    private Long likes;
    private Long views;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "videoEntity")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "videoEntity")
    private List<LikeEntity> likeEntities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "videoEntity")
    private List<ViewEntity> viewEntities = new ArrayList<>();

    private Long numberOfComments;



}
