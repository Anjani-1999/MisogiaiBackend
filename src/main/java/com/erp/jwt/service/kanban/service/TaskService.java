package com.erp.jwt.service.kanban.service;

import com.erp.jwt.request.kanban.TaskRequest;
import com.erp.jwt.response.kanban.TaskResponse;

public interface TaskService {

    Object getAllTasks(String authorizationHeader);

    Object getTaskById(Long taskId);

    TaskResponse createTask(TaskRequest taskRequest);

    Object updateTask(String authorizationHeader, Long taskId, Object taskRequest);

    Object deleteTask(String authorizationHeader, Long taskId);
}
