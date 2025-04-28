package com.erp.jwt.controller;

import com.erp.jwt.request.RegisterUserRequest;
import com.erp.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN','ROLE_USER')")
    @PreAuthorize("hasAnyAuthority('SCOPE_READ')")
    @GetMapping("/welcome-message")
    public ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication){
        return ResponseEntity.ok("Welcome to the JWT Tutorial:"+authentication.getName()+"with scope:"+authentication.getAuthorities());
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/manager-message")
    public ResponseEntity<String> getManagerData(Principal principal){
        return ResponseEntity.ok("Manager::"+principal.getName());

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin-message")
    public ResponseEntity<String> getAdminData(@RequestParam("message") String message, Principal principal){
        return ResponseEntity.ok("Admin::"+principal.getName()+" has this message:"+message);

    }

    @PostMapping("/register/user")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUserRequest request){
        Map<Object,Object> map = new HashMap<>();
        RegisterUserRequest response = userService.registerUser(request);
        map.put("data",response);
        return ResponseEntity.ok(map);
    }
}
