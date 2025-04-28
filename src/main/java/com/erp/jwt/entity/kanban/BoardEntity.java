package com.erp.jwt.entity.kanban;


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
@Table(name="BOARD")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "boardId"
)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long boardId;
    private Long userId;
    private String boardName;
    private String boardDescription;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "boardEntity")
    private List<ColumnEntity> columns = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "board")
    private List<TaskEntity> tasks = new ArrayList<>();
}
