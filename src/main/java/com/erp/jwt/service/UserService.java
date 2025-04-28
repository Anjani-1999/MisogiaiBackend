package com.erp.jwt.service;

import com.erp.jwt.request.RegisterUserRequest;

public interface UserService {

    RegisterUserRequest registerUser(RegisterUserRequest registerUserRequest);
}
