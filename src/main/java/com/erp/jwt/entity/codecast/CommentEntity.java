package com.erp.jwt.entity.codecast;

import com.erp.jwt.entity.BaseEntity;
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
@Table(name="COMMENT")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "commentId"
)
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long commentId;
    private Long userId;
    private String comment;
    private Long likes;
    private Long dislikes;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "commentEntity")
    private List<LikeEntity> likeEntities = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "video_id_fk", referencedColumnName = "videoId")
    private VideoEntity videoEntity;
}
