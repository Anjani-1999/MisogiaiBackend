package com.erp.jwt.service.kanban.service;

import com.erp.jwt.entity.kanban.ColumnEntity;
import com.erp.jwt.request.kanban.ColumnRequest;

import java.util.List;

public interface ColumnService {
    /**
     * Create a new column in the specified board.
     *
     * @param boardId       The ID of the board where the column will be created.
     * @param columnRequest The request object containing the details of the column to be created.
     * @return A list of ColumnEntity objects representing the created columns.
     */
    List<ColumnEntity> createColumn(Long boardId, ColumnRequest columnRequest);

}
