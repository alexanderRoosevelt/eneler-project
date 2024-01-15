package com.project.eneler.model.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoViewResponse {
  private List<VideoResponse> responseList;
}
