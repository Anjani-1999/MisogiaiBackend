package com.erp.jwt.entity.kanban;

import com.erp.jwt.entity.BaseEntity;
import com.erp.jwt.entity.UserInfoEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TASK")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "taskId"
)
public class TaskEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private Long taskId;
    private String taskName;
    private LocalDate dueDate;
    private String description;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "taskEntity")
    private List<AssigneeEntity> assignees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "board_id_fk", referencedColumnName = "boardId")
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private UserInfoEntity userInfo;
}
