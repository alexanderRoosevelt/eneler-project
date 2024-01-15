package com.project.eneler.service;

import com.project.eneler.exception.AuthException;
import com.project.eneler.model.dto.response.VideoResponse;
import com.project.eneler.model.dto.response.VideoViewResponse;

public interface UserVideoViewService {

  VideoViewResponse getListVideos(String userId) throws AuthException;
  VideoResponse getVideo(String userId, String videoId);
}
