package com.erp.jwt.request.codecast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequest {

    private Long tagId;
    private String tag;
}
