package com.erp.jwt.service.kanban.impl;

import com.erp.jwt.dao.TaskDaoService;
import com.erp.jwt.entity.kanban.TaskEntity;
import com.erp.jwt.mapper.GlobalMapper;
import com.erp.jwt.request.kanban.TaskRequest;
import com.erp.jwt.response.kanban.TaskResponse;
import com.erp.jwt.service.kanban.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final GlobalMapper globalMapper;
    private final TaskDaoService taskDaoService;

    public TaskServiceImpl(GlobalMapper globalMapper, TaskDaoService taskDaoService) {
        this.globalMapper = globalMapper;
        this.taskDaoService = taskDaoService;
    }

    @Override
    public Object getAllTasks(String authorizationHeader) {
        return null;
    }

    @Override
    public Object getTaskById(Long taskId) {
        return null;
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        TaskResponse taskResponse = new TaskResponse();
        try{
            TaskEntity taskEntity = globalMapper.dtoToEntity(taskRequest,TaskEntity.class);
            taskEntity = taskDaoService.saveTask(taskEntity);
            TaskRequest taskRequest1 = globalMapper.entityToDto(taskEntity,TaskRequest.class);
            taskResponse.setData(taskRequest1);
            taskResponse.setMessage("Task created successfully");
            taskResponse.setStatus(200);
            return taskResponse;
        }catch (Exception e){
            taskResponse.setMessage(e.getMessage());
            taskResponse.setStatus(500);
            return taskResponse;
        }
    }

    @Override
    public Object updateTask(String authorizationHeader, Long taskId, Object taskRequest) {
        return null;
    }

    @Override
    public Object deleteTask(String authorizationHeader, Long taskId) {
        return null;
    }
}
