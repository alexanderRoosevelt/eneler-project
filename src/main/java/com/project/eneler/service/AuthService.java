package com.project.eneler.service;

import com.project.eneler.exception.AuthException;
import com.project.eneler.model.dto.request.user.AuthRequest;
import com.project.eneler.model.dto.response.AuthResponse;

public interface AuthService {
  AuthResponse signIn(AuthRequest authRequest) throws AuthException;
}
