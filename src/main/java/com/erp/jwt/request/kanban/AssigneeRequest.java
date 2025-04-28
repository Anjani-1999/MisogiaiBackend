package com.erp.jwt.request.kanban;

import com.erp.jwt.entity.kanban.TaskEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssigneeRequest {
    private Long assigneeId;
    private Long userId;
    private String userName;
    private TaskRequest taskEntity;
}
