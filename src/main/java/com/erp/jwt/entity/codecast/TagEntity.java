package com.erp.jwt.entity.codecast;

import com.erp.jwt.entity.kanban.BoardEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TAG")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "tagId"
)
public class TagEntity {

    @Id
    @GeneratedValue
    private Long tagId;
    private String tag;

    @ManyToOne
    @JoinColumn(name = "video_id_fk", referencedColumnName = "videoId")
    private VideoEntity videoEntity;

}
