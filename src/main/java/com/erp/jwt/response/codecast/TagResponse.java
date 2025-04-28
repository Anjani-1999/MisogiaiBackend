package com.erp.jwt.response.codecast;

import com.erp.jwt.request.codecast.TagRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {
    private List<TagRequest> tags;
    private TagRequest data;
    private String message;
    private Integer status;
}
