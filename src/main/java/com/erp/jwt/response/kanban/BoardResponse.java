package com.erp.jwt.response.kanban;

import com.erp.jwt.request.kanban.BoardRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {
    private BoardRequest data;
    private String message;
    private Integer status;
}
