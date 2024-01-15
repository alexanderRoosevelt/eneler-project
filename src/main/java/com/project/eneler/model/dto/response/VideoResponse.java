package com.project.eneler.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoResponse {
  private String id;
  private String shortInfo;
  private String fullInfo;
  private String url;
  private String imageUrl;
  private String access;
  private boolean watchedUntilEnd;
}
