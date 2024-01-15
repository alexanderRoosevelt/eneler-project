package com.project.eneler.controller.user;


import com.project.eneler.exception.AuthException;
import com.project.eneler.model.dto.response.VideoResponse;
import com.project.eneler.model.dto.response.VideoViewResponse;
import com.project.eneler.service.UserVideoViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/video")
public class VideoController {

  private final UserVideoViewService userVideoViewService;

  @GetMapping("/{user_id}")
  public ResponseEntity<VideoViewResponse> getListVideos(@PathVariable("user_id") String userId)
      throws AuthException {
    return ResponseEntity.ok(userVideoViewService.getListVideos(userId));
  }

  @GetMapping("/{user_id}/{video_id}")
  public ResponseEntity<VideoResponse> getVideo(@PathVariable("user_id") String userId,
      @PathVariable("video_id") String videoId) {
    return ResponseEntity.ok(userVideoViewService.getVideo(userId, videoId));
  }

}
