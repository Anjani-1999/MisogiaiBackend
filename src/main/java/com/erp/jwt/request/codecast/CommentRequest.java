package com.erp.jwt.request.codecast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private Long commentId;
    private Long videoId;
    private Long userId;
    private String comment;
    private Long likes;
    private Long dislikes;
}
