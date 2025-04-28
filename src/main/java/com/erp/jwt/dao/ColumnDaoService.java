package com.erp.jwt.dao;

import com.erp.jwt.entity.kanban.ColumnEntity;
import com.erp.jwt.repo.kanban.ColumnRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnDaoService {

    private final ColumnRepo columnRepo;

    public ColumnDaoService(ColumnRepo columnRepo) {
        this.columnRepo = columnRepo;
    }

    public List<ColumnEntity> getAllColumns() {
        return columnRepo.findAll();
    }

    public ColumnEntity getColumnById(Long columnId) {
        return columnRepo.findById(columnId).orElse(null);
    }

    public ColumnEntity createColumn(ColumnEntity columnEntity) {
        return columnRepo.save(columnEntity);
    }
}
