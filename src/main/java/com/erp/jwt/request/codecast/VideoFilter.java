package com.erp.jwt.request.codecast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoFilter {

    private Long page;
    private Long pageSize;
    private List<Long> userId;
    private String searchBox;
    private List<String> category;
    private List<String> tags;
}
