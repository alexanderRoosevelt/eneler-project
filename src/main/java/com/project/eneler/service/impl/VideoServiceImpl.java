package com.project.eneler.service.impl;

import com.project.eneler.model.dto.request.video.VideoRequest;
import com.project.eneler.model.dto.response.VideoResponse;
import com.project.eneler.model.entity.VideoEntity;
import com.project.eneler.repository.VideoRepository;
import com.project.eneler.service.VideoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

  private final VideoRepository videoRepository;

  @Override
  public List<VideoResponse> getList() {

    List<VideoEntity> videoEntities = videoRepository.findAll();

    if (videoEntities.isEmpty()) {
      return new ArrayList<>();
    }

    List<VideoResponse> videoResponses = new ArrayList<>();

    for (VideoEntity videoEntity : videoEntities) {
      videoResponses.add(
          VideoResponse.builder()
              .id(String.valueOf(videoEntity.getId()))
              .url(videoEntity.getUrl())
              .access(String.valueOf(videoEntity.getAccess()))
              .shortInfo(videoEntity.getShortInfo())
              .fullInfo(videoEntity.getFullInfo())
              .imageUrl(videoEntity.getImageUrl())
              .watchedUntilEnd(null)
              .build()
      );
    }

    return videoResponses;
  }

  @Override
  public VideoResponse getVideo(String videoId) {

    Optional<VideoEntity> videoEntityOptional = videoRepository.findById(Long.valueOf(videoId));

    if (videoEntityOptional.isEmpty()) {
      return null;
    }

    VideoEntity videoEntity = videoEntityOptional.get();

    return VideoResponse.builder()
        .id(String.valueOf(videoEntity.getId()))
        .url(videoEntity.getUrl())
        .access(String.valueOf(videoEntity.getAccess()))
        .shortInfo(videoEntity.getShortInfo())
        .fullInfo(videoEntity.getFullInfo())
        .imageUrl(videoEntity.getImageUrl())
        .watchedUntilEnd(null)
        .build();
  }

  @Override
  @Transactional
  public boolean createVideo(VideoRequest videoRequest) {
    try {
      VideoEntity videoEntity = new VideoEntity();
      videoEntity.setUrl(videoRequest.getUrl());
      videoEntity.setImageUrl(videoRequest.getImageUrl());
      videoEntity.setFullInfo(videoRequest.getFullInfo());
      videoEntity.setShortInfo(videoRequest.getShortInfo());
      videoEntity.setAccess(videoRequest.getAccess());
      videoRepository.save(videoEntity);
      return true;
    } catch (Exception ex) {
      log.error(ex.toString());
      return false;
    }
  }

  @Override
  @Transactional
  public boolean deleteVideo(String videoId) {
    Optional<VideoEntity> videoEntityOptional = videoRepository.findById(Long.valueOf(videoId));

    if (videoEntityOptional.isEmpty()) {
      return false;
    }
    videoRepository.delete(videoEntityOptional.get());
    return true;
  }
}
