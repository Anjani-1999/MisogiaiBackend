package com.erp.jwt.service;

import com.erp.jwt.entity.UserInfoEntity;
import com.erp.jwt.repo.UserInfoRepo;
import com.erp.jwt.request.RegisterUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserInfoRepo userInfoRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserInfoRepo userInfoRepo, PasswordEncoder passwordEncoder) {
        this.userInfoRepo = userInfoRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public RegisterUserRequest registerUser(RegisterUserRequest registerUserRequest) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        String encodedPassword = passwordEncoder.encode("AAAAAA");

        userInfoEntity.setUserName(registerUserRequest.getUsername());
        userInfoEntity.setPassword(encodedPassword);
        userInfoEntity.setEmailId(registerUserRequest.getEmailId());
        userInfoEntity.setRoles("ROLE_USER,ROLE_ADMIN,ROLE_MANAGER");
        userInfoRepo.save(userInfoEntity);
        return registerUserRequest;
    }
}
