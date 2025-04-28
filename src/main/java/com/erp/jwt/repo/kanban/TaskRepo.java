package com.erp.jwt.repo.kanban;

import com.erp.jwt.entity.kanban.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
