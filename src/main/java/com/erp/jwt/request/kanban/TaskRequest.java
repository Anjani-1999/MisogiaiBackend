package com.erp.jwt.request.kanban;

import com.erp.jwt.entity.UserInfoEntity;
import com.erp.jwt.entity.kanban.AssigneeEntity;
import com.erp.jwt.entity.kanban.BoardEntity;
import com.erp.jwt.records.UserRegistrationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private Long taskId;
    private String taskName;
    private LocalDate dueDate;
    private String description;
    private List<AssigneeRequest> assignees = new ArrayList<>();
    private BoardRequest board;
    private UserRegistrationDto userInfo;
}
