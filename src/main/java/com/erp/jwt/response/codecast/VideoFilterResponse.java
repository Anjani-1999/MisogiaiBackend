package com.erp.jwt.response.codecast;

import com.erp.jwt.request.codecast.UploadVideRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoFilterResponse {

    private Long page;
    private Long pageSize;
    private Long totalResults;
    private Long totalPages;
    private List<UploadVideRequest> data;
    private String message;
    private Integer status;
}
