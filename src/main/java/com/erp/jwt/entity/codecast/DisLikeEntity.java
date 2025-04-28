package com.erp.jwt.entity.codecast;

import com.erp.jwt.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "DIS_LIKE_ENTITY",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "video_id_fk"}),
                @UniqueConstraint(columnNames = {"user_id", "comment_id_fk"})
        }
)
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "disLikeId"
)
public class DisLikeEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long disLikeId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "video_id_fk", referencedColumnName = "videoId")
    private VideoEntity videoEntity;

    @ManyToOne
    @JoinColumn(name = "comment_id_fk", referencedColumnName = "commentId")
    private CommentEntity commentEntity;
}
