package com.erp.jwt.repo.kanban;

import com.erp.jwt.entity.kanban.AssigneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepo extends JpaRepository<AssigneeEntity, Long> {
}
