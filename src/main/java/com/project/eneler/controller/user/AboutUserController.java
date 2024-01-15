package com.project.eneler.controller.user;


import com.project.eneler.exception.AboutInfoException;
import com.project.eneler.model.dto.request.AboutUserRequest;
import com.project.eneler.service.AboutUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/about-user")
public class AboutUserController {

  private final AboutUserService aboutUserService;

  @PostMapping("/add")
  public ResponseEntity<Boolean> auth(@RequestBody AboutUserRequest aboutUserRequest)
      throws AboutInfoException {
    return ResponseEntity.ok(aboutUserService.addInfoAboutUser(aboutUserRequest));
  }
}
