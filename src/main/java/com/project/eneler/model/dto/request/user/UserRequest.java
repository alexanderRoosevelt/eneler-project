package com.project.eneler.model.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRequest {
  @JsonProperty("user_name")
  private String username;
  private String email;
  private String role;
  private boolean registered;
}
