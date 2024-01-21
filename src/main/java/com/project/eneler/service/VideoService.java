package com.project.eneler.service;

import com.project.eneler.model.dto.request.video.VideoRequest;
import com.project.eneler.model.dto.response.VideoResponse;
import java.util.List;

public interface VideoService {
  List<VideoResponse> getList();

  VideoResponse getVideo(String videoId);

  boolean createVideo(VideoRequest videoRequest);
  boolean deleteVideo(String videoId);
}
