package com.project.eneler.controller.admin;


import com.project.eneler.model.dto.request.UserRequest;
import com.project.eneler.model.dto.request.VideoRequest;
import com.project.eneler.model.dto.response.UserResponse;
import com.project.eneler.model.dto.response.VideoResponse;
import com.project.eneler.service.UserService;
import com.project.eneler.service.VideoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final UserService userService;
  private final VideoService videoService;

  // work with users

  @GetMapping("/user/list")
  public ResponseEntity<List<UserResponse>> getUserList() {
    return ResponseEntity.ok(userService.getList());
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable("user_id") String userId) {
    return ResponseEntity.ok(userService.getUser(userId));
  }

  @PostMapping("/user/create")
  public ResponseEntity<Boolean> createUser(@RequestBody UserRequest userRequest) {
    return ResponseEntity.ok(userService.createUser(userRequest));
  }

  @PutMapping("/user/update/{user_id}")
  public ResponseEntity<Boolean> updateUser(@PathVariable("user_id") String userId,
      @RequestBody UserRequest userRequest) {
    return ResponseEntity.ok(userService.updateUser(userId, userRequest));
  }

  @DeleteMapping("/user/delete/{user_id}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable("user_id") String userId) {
    return ResponseEntity.ok(userService.deleteUser(userId));
  }

  // work with videos
  @GetMapping("/video/list")
  public ResponseEntity<List<VideoResponse>> getVideoList() {
    return ResponseEntity.ok(videoService.getList());
  }

  @GetMapping("/video/{video_id}")
  public ResponseEntity<VideoResponse> getVideo(@PathVariable("video_id") String videoId) {
    return ResponseEntity.ok(videoService.getVideo(videoId));
  }

  @PostMapping("/video/create")
  public ResponseEntity<Boolean> createVideo(@RequestBody VideoRequest videoRequest) {
    return ResponseEntity.ok(videoService.createVideo(videoRequest));
  }

  @DeleteMapping("/video/delete/{video_id}")
  public ResponseEntity<Boolean> deleteVideo(@PathVariable("video_id") String videoId) {
    return ResponseEntity.ok(videoService.deleteVideo(videoId));
  }
}
