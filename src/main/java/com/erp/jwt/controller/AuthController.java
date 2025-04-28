package com.erp.jwt.controller;

import com.erp.jwt.records.UserRegistrationDto;
import com.erp.jwt.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/api/validate/token")
    public ResponseEntity<Object> getFirstWelcomeMessage(Authentication authentication){
        Map<String, Object> map = new HashMap<>();
        try{
            Map<String, Object> map1 = new HashMap<>();
            map1.put("user", authentication.getName());
            map1.put("authenticated",authentication.isAuthenticated());
            map.put("data",map1);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            map.put("authenticated",false);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(Authentication authentication, HttpServletResponse response) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("data",authService.getJwtTokenAfterAuthentication(authentication,response));

            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            log.error("[AuthController:authenticateUser] Exception while authenticating the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("hasAuthority('SCOPE_REFRESH_TOKEN')")
    @PostMapping ("/refresh-token")
    public ResponseEntity<?> getAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return ResponseEntity.ok(authService.getAccessTokenUsingRefreshToken(authorizationHeader));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto,
                                          BindingResult bindingResult, HttpServletResponse httpServletResponse){

        log.info("[AuthController:registerUser]Signup Process Started for user:{}",userRegistrationDto.getUserName());
        if (bindingResult.hasErrors()) {
            List<String> errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            log.error("[AuthController:registerUser]Errors in user:{}",errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.ok(authService.registerUser(userRegistrationDto,httpServletResponse));
    }

}
