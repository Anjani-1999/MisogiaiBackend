package com.erp.jwt.request.kanban;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {
    private Long boardId;
    private String boardName;
    private Long userId;
    private String boardDescription;
    private List<ColumnRequest> columns;
}
