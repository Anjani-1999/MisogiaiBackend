package com.erp.jwt.response;

import com.erp.jwt.request.codecast.BulkUploadVideoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkUploadVideResponse {

    private BulkUploadVideoRequest data;
    private String message;
    private Integer status;
}
