package com.project.eneler.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse
{
  @JsonProperty("user_id")
  private String userId;
  @JsonProperty("user_name")
  private String username;
  private String email;
  private String role;
  private boolean registered;
}
