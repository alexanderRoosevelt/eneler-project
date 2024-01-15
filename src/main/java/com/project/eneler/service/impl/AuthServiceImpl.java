package com.project.eneler.service.impl;

import com.project.eneler.exception.AuthException;
import com.project.eneler.model.dto.request.AuthRequest;
import com.project.eneler.model.dto.response.AuthResponse;
import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.model.enums.Role;
import com.project.eneler.repository.AuthRepository;
import com.project.eneler.service.AuthService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthRepository authRepository;

  @Override
  public AuthResponse signIn(AuthRequest authRequest) throws AuthException {

    if (authRequest == null || authRequest.getEmail().isEmpty()) {
      log.error("Данные не заполнены!");
      throw new AuthException("Поля необходимо заполнить!");
    }

    log.info("Проверка наличия пользователя");
    Optional<UserEntity> userEntityOptional = authRepository
        .findByEmail(authRequest.getEmail());

    if (userEntityOptional.isPresent()) {
      return AuthResponse.builder()
          .userId(userEntityOptional.get().getUserTokenId().toString())
          .email(userEntityOptional.get().getEmail())
          .registered(true)
          .username(userEntityOptional.get().getUsername())
          .role(userEntityOptional.get().getRole().name())
          .build();
    }

    UserEntity user = new UserEntity();
    user.setEmail(authRequest.getEmail());
    user.setRole(Role.USER);
    user.setCreatedDate(LocalDateTime.now());
    user.setUsername(authRequest.getUsername());
    user.setRegistered(true);
    user.setUserTokenId(UUID.randomUUID());
    UserEntity savedUser = authRepository.save(user);

    return AuthResponse.builder()
        .userId(savedUser.getUserTokenId().toString())
        .email(savedUser.getEmail())
        .registered(false)
        .username(savedUser.getUsername())
        .role(savedUser.getRole().name())
        .build();
  }
}
