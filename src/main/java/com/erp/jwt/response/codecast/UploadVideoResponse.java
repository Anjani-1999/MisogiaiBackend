package com.erp.jwt.response.codecast;

import com.erp.jwt.request.codecast.UploadVideRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadVideoResponse {

    private UploadVideRequest data;
    private String message;
    private Integer status;
}
