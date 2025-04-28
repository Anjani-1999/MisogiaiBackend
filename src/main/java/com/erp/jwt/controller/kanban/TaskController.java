package com.erp.jwt.controller.kanban;


import com.erp.jwt.records.UserRegistrationDto;
import com.erp.jwt.request.kanban.TaskRequest;
import com.erp.jwt.service.kanban.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create/task")
    public ResponseEntity<?> createTaskHandler(@Valid @RequestBody TaskRequest taskRequest){

        log.info("creating task:{}",taskRequest);
        try{
            return ResponseEntity.ok(taskService.createTask(taskRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
