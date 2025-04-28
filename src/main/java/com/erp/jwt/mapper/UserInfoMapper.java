package com.erp.jwt.mapper;

import com.erp.jwt.entity.UserInfoEntity;
import com.erp.jwt.records.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoMapper {
    private final PasswordEncoder passwordEncoder;
    public UserInfoEntity convertToEntity(UserRegistrationDto userRegistrationDto) {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserName(userRegistrationDto.getUserName());
        userInfoEntity.setEmailId(userRegistrationDto.getUserEmail());
        userInfoEntity.setMobileNumber(userRegistrationDto.getUserMobileNo());
        userInfoEntity.setRoles(userRegistrationDto.getUserRole());
        userInfoEntity.setPassword(passwordEncoder.encode(userRegistrationDto.getUserPassword()));
        return userInfoEntity;
    }
}
