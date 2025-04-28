package com.erp.jwt.request.kanban;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnRequest {
    private String columnName;
    private Long columnId;

}
