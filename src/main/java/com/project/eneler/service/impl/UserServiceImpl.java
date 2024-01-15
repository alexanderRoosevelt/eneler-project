package com.project.eneler.service.impl;

import com.project.eneler.model.dto.request.UserRequest;
import com.project.eneler.model.dto.response.UserResponse;
import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.model.enums.Role;
import com.project.eneler.repository.AuthRepository;
import com.project.eneler.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final AuthRepository authRepository;

  @Override
  public List<UserResponse> getList() {
    List<UserEntity> userEntityList = authRepository.findAll();

    if (userEntityList.isEmpty()) {
      return new ArrayList<>();
    }

    List<UserResponse> result = new ArrayList<>();

    for (UserEntity user : userEntityList) {
      result.add(
          UserResponse.builder()
              .userId(user.getUserTokenId() == null ? "" : user.getUserTokenId().toString())
              .email(user.getEmail())
              .registered(user.isRegistered())
              .role(user.getRole().name())
              .username(user.getUsername())
              .build()
      );
    }
    return result;
  }

  @Override
  public UserResponse getUser(String userId) {

    Optional<UserEntity> userResponse = authRepository.findByUserTokenId(UUID.fromString(userId));

    if (userResponse.isEmpty()) {
      return null;
    }

    UserEntity user = userResponse.get();

    return UserResponse.builder()
        .userId(user.getUserTokenId() == null ? "" : user.getUserTokenId().toString())
        .email(user.getEmail())
        .registered(user.isRegistered())
        .role(user.getRole().name())
        .username(user.getUsername())
        .build();
  }

  @Override
  @Transactional
  public boolean createUser(UserRequest userRequest) {
    try {
      UserEntity user = new UserEntity();
      user.setCreatedDate(LocalDateTime.now());
      user.setRegistered(true);
      user.setUserTokenId(UUID.randomUUID());
      user.setEmail(userRequest.getEmail());
      user.setUsername(userRequest.getUsername());
      user.setRole(Role.valueOf(userRequest.getRole()));
      authRepository.save(user);
      return true;
    } catch (Exception ex) {
      log.error(ex.toString());
      return false;
    }
  }

  @Override
  @Transactional
  public boolean updateUser(String userId, UserRequest userRequest) {
    try {
      Optional<UserEntity> userResponse = authRepository.findByUserTokenId(UUID.fromString(userId));

      if (userResponse.isEmpty()) {
        return false;
      }
      UserEntity user = userResponse.get();
      user.setRole(Role.valueOf(userRequest.getRole()));
      user.setUsername(userRequest.getUsername());
      user.setEmail(user.getEmail());
      authRepository.save(user);
      return true;
    } catch (Exception ex) {
      log.error(ex.toString());
      return false;
    }
  }

  @Override
  @Transactional
  public boolean deleteUser(String userId) {
    Optional<UserEntity> userResponse = authRepository.findByUserTokenId(UUID.fromString(userId));
    if (userResponse.isEmpty()) {
      return false;
    }

    authRepository.delete(userResponse.get());
    return true;
  }
}
