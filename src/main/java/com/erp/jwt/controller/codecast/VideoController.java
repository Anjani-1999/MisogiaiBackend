package com.erp.jwt.controller.codecast;


import com.erp.jwt.request.codecast.BulkUploadVideoRequest;
import com.erp.jwt.request.codecast.CommentRequest;
import com.erp.jwt.request.codecast.UploadVideRequest;
import com.erp.jwt.request.codecast.VideoFilter;
import com.erp.jwt.request.kanban.TaskRequest;
import com.erp.jwt.service.codecast.VideoService;
import com.erp.jwt.service.kanban.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/api/upload/video")
    public ResponseEntity<?> createTaskHandler(@Valid @RequestBody UploadVideRequest uploadVideRequest){

        log.info("creating task:{}",uploadVideRequest);
        try{
            return ResponseEntity.ok(videoService.uploadVideo(uploadVideRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/edit/video")
    public ResponseEntity<?> editTaskHandler(@Valid @RequestBody UploadVideRequest uploadVideRequest){

        log.info("editing task:{}",uploadVideRequest);
        try{
            return ResponseEntity.ok(videoService.editVideo(uploadVideRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/bulk/upload/video")
    public ResponseEntity<?> uploadBulkVideo(@Valid @RequestBody BulkUploadVideoRequest uploadVideRequest){

        log.info("creating task:{}",uploadVideRequest);
        try{
            return ResponseEntity.ok(videoService.uploadVideosInBulk(uploadVideRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/get/video/filter")
    public ResponseEntity<?> getFilteredVideoHandler(@Valid @RequestBody VideoFilter videoFilter){

        log.info("getting video filter:{}",videoFilter);
        try{
            return ResponseEntity.ok(videoService.getAllVideosByFilter(videoFilter));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/get/video/{video-id}")
    public ResponseEntity<?> getVideoByIdHandler(@PathVariable("video-id") Long videoId) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("data",videoService.getVideoById(videoId));

            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            log.error("[AuthController:authenticateUser] Exception while authenticating the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/api/delete/video/{video-id}")
    public ResponseEntity<?> deleteVideo(@PathVariable("video-id") Long videoId) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("data",videoService.deleteVideo(videoId));

            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            log.error("[AuthController:authenticateUser] Exception while authenticating the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/api/get/comments/{video-id}")
    public ResponseEntity<?> getCommentsByVideoId(@PathVariable("video-id") Long videoId) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("data",videoService.getCommentsByVideoId(videoId));

            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            log.error("[AuthController:authenticateUser] Exception while authenticating the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/api/video/{user-id}")
    public ResponseEntity<?> getVideoByUserIdHandler(@PathVariable("user-id") Long userId) {

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("data",videoService.getAllVideosByUser(userId));

            return new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            log.error("[AuthController:authenticateUser] Exception while authenticating the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/api/like/video")
    public ResponseEntity<?> likeVideoHandler(@Valid @RequestBody UploadVideRequest uploadVideRequest){
        log.info("creating task:{}",uploadVideRequest);
        try{
            return ResponseEntity.ok(videoService.likeVideo(uploadVideRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/tags")
    public ResponseEntity<?> getTagsHandler(){
        try{
            return ResponseEntity.ok(videoService.getTags());
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/view/video")
    public ResponseEntity<?> countViewVideoHandler(@Valid @RequestBody UploadVideRequest uploadVideRequest){
        log.info("creating task:{}",uploadVideRequest);
        try{
            return ResponseEntity.ok(videoService.view(uploadVideRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/comment/video")
    public ResponseEntity<?> commentVideoHandler(@Valid @RequestBody CommentRequest commentRequest){
        log.info("creating task:{}",commentRequest);
        try{
            return ResponseEntity.ok(videoService.commentVideo(commentRequest));
        }catch (Exception e){
            log.error("[AuthController:registerUser] Exception while registering the user due to :"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
