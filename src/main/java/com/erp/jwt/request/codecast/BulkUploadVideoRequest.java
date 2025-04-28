package com.erp.jwt.request.codecast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulkUploadVideoRequest {
    private List<UploadVideRequest> videos;
}
