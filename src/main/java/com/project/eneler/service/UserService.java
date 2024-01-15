package com.project.eneler.service;

import com.project.eneler.model.dto.request.UserRequest;
import com.project.eneler.model.dto.response.UserResponse;
import java.util.List;

public interface UserService {
  List<UserResponse> getList();

  UserResponse getUser(String userId);

  boolean createUser(UserRequest userRequest);

  boolean updateUser(String userId, UserRequest userRequest);

  boolean deleteUser(String userId);
}
