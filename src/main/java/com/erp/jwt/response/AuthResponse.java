package com.erp.jwt.response;

import com.erp.jwt.constants.TokenType;
import com.erp.jwt.request.kanban.BoardRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("access_token_expiry")
    private int accessTokenExpiry;

    @JsonProperty("token_type")
    private TokenType tokenType;

    @JsonProperty("user_name")
    private String userName;

    private Long userId;

    private BoardRequest boardRequest;
}
