package com.project.eneler.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class VideoRequest {
  @JsonProperty("short_info")
  private String shortInfo;
  @JsonProperty("full_info")
  private String fullInfo;
  private String url;
  @JsonProperty("image_url")
  private String imageUrl;
  private LocalDate access;
}
