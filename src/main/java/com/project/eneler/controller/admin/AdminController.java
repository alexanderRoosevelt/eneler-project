package com.project.eneler.controller.admin;


import com.project.eneler.model.dto.response.UserResponse;
import com.project.eneler.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final UserService userService;

  //work with users

  @GetMapping("/user-list")
  public ResponseEntity<List<UserResponse>> getUserList() {
      return ResponseEntity.ok(userService.getList());
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable("user_id") String userId) {
    return ResponseEntity.ok(userService.getUser(userId));
  }

}
