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
        name = "VIEW_ENTITY",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "video_id_fk"}),
        }
)
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "viewId"
)
public class ViewEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long viewId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "video_id_fk", referencedColumnName = "videoId")
    private VideoEntity videoEntity;
}
