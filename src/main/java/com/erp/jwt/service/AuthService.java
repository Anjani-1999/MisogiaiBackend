package com.erp.jwt.service;

import com.erp.jwt.records.UserRegistrationDto;
import com.erp.jwt.response.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;

public interface AuthService {

    AuthResponse getJwtTokenAfterAuthentication(Authentication authentication, HttpServletResponse response);

    Object getAccessTokenUsingRefreshToken(String authorizationHeader);

    AuthResponse registerUser(@Valid UserRegistrationDto userRegistrationDto, HttpServletResponse httpServletResponse);
}
