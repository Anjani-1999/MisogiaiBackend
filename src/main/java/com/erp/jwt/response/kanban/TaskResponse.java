package com.erp.jwt.response.kanban;

import com.erp.jwt.request.kanban.TaskRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private TaskRequest data;
    private String message;
    private Integer status;
}
