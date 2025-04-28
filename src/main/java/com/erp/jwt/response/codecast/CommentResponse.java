package com.erp.jwt.response.codecast;

import com.erp.jwt.request.codecast.CommentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private List<CommentRequest> comments;
    private CommentRequest data;
    private String message;
    private Integer status;
}
