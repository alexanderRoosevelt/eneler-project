package com.project.eneler.model.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AboutUserRequest {

  @JsonProperty("user_id")
  private String userId;
  @JsonProperty("about_info")
  @Size(max = 1000, message = "About info should be at most 100 characters long")
  private String aboutInfo;

}
