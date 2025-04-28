package com.erp.jwt.dao;

import com.erp.jwt.entity.kanban.TaskEntity;
import com.erp.jwt.repo.kanban.TaskRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskDaoService {

    private final TaskRepo taskRepo;

    public TaskDaoService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public TaskEntity saveTask(TaskEntity taskEntity) {
        return taskRepo.save(taskEntity);
    }
}
