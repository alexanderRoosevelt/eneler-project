package com.project.eneler.model.dto.request.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthRequest {

  private String email;
  private String username;

}
