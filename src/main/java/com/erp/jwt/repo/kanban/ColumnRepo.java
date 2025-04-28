package com.erp.jwt.repo.kanban;

import com.erp.jwt.entity.kanban.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepo extends JpaRepository<ColumnEntity,Long> {
}
