package com.erp.jwt.entity.kanban;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ASSIGNEE")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "assigneeId"
)
public class AssigneeEntity {

    @Id
    @GeneratedValue
    private Long assigneeId;
    private Long userId;
    private String userName;

    @ManyToOne
    @JoinColumn(name = "task_id_fk", referencedColumnName = "taskId")
    private TaskEntity taskEntity;
}
