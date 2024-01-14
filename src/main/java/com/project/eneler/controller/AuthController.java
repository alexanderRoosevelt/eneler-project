package com.project.eneler.controller;


import com.project.eneler.exception.AuthException;
import com.project.eneler.model.dto.request.AuthRequest;
import com.project.eneler.model.dto.response.AuthResponse;
import com.project.eneler.service.AuthService;
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
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/")
  public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest)
      throws AuthException {
    log.info("Авторизация юзера - " + authRequest.toString());
    return ResponseEntity.ok(authService.signIn(authRequest));
  }
}
