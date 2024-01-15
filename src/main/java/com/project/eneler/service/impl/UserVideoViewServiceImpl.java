package com.project.eneler.service.impl;

import com.project.eneler.exception.AuthException;
import com.project.eneler.model.dto.response.VideoResponse;
import com.project.eneler.model.dto.response.VideoViewResponse;
import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.model.entity.UserVideoViewEntity;
import com.project.eneler.model.entity.UserVideoViewId;
import com.project.eneler.model.entity.VideoEntity;
import com.project.eneler.repository.AuthRepository;
import com.project.eneler.repository.UserVideoViewRepository;
import com.project.eneler.repository.VideoRepository;
import com.project.eneler.service.UserVideoViewService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserVideoViewServiceImpl implements UserVideoViewService {

  private final AuthRepository authRepository;
  private final VideoRepository videoRepository;
  private final UserVideoViewRepository userVideoViewRepository;


  @Override
  public VideoViewResponse getListVideos(String userId) throws AuthException {
    Optional<UserEntity> userEntity = authRepository.findByUserTokenId(UUID.fromString(userId));
    if (userEntity.isEmpty()) {
      throw new AuthException(String.format("Такого пользователя не существует - id - %s", userId));
    }

    List<UserVideoViewEntity> userVideoViewEntityList = userVideoViewRepository.findAllByUser(userEntity.get());

    if (userVideoViewEntityList.isEmpty()) {
      log.info(String.format("Данных по видео нет для пользователя с id - %s", userId));
      return VideoViewResponse.builder()
          .responseList(new ArrayList<>())
          .build();
    }

    List<VideoResponse> videoResponseList = new ArrayList<>();

    for (UserVideoViewEntity entity : userVideoViewEntityList) {
      videoResponseList.add(
          VideoResponse.builder()
              .id(entity.getVideo().getId().toString())
              .url(entity.getVideo().getUrl())
              .imageUrl(entity.getVideo().getImageUrl())
              .fullInfo(entity.getVideo().getFullInfo())
              .shortInfo(entity.getVideo().getShortInfo())
              .access(entity.getVideo().getAccess().toString())
              .watchedUntilEnd(entity.isWatchedUntilEnd())
              .build()
      );
    }
    return VideoViewResponse.builder()
        .responseList(videoResponseList)
        .build();
  }

  @Override
  public VideoResponse getVideo(String userId, String videoId) {

    Optional<UserEntity> userEntity = authRepository.findByUserTokenId(UUID.fromString(userId));
    Optional<VideoEntity> videoEntity = videoRepository.findById(Long.valueOf(videoId));

    if(userEntity.isEmpty() || videoEntity.isEmpty()) {
      log.error(String.format("Данных нет по userId - %s или videoId - %s", userId, videoId));
      return null;
    }

    UserVideoViewId userVideoViewId = new UserVideoViewId();
    userVideoViewId.setUserId(userEntity.get().getId());
    userVideoViewId.setVideoId(videoEntity.get().getId());

    Optional<UserVideoViewEntity> optionalUserVideoView = userVideoViewRepository.findById(userVideoViewId);

    if(optionalUserVideoView.isEmpty()) {
      log.error(String.format("Данных нет по userId - %s и videoId - %s", userId, videoId));
      return null;
    }

    UserVideoViewEntity entity = optionalUserVideoView.get();

    return VideoResponse.builder()
        .id(entity.getVideo().getId().toString())
        .url(entity.getVideo().getUrl())
        .imageUrl(entity.getVideo().getImageUrl())
        .fullInfo(entity.getVideo().getFullInfo())
        .shortInfo(entity.getVideo().getShortInfo())
        .access(entity.getVideo().getAccess().toString())
        .watchedUntilEnd(entity.isWatchedUntilEnd())
        .build();
  }
}
