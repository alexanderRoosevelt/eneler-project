package com.project.eneler.service.impl;

import com.project.eneler.exception.AboutInfoException;
import com.project.eneler.model.dto.request.user.AboutUserRequest;
import com.project.eneler.model.entity.AboutUserEntity;
import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.repository.AboutUserRepository;
import com.project.eneler.repository.AuthRepository;
import com.project.eneler.service.AboutUserService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AboutUserServiceImpl implements AboutUserService {

  private final AuthRepository authRepository;
  private final AboutUserRepository aboutUserRepository;

  @Override
  @Transactional
  public boolean addInfoAboutUser(AboutUserRequest aboutUserRequest) throws AboutInfoException {
      if (aboutUserRequest == null) {
        throw new AboutInfoException("Информации о пользователе не передано!");
      }

      Optional<UserEntity> userEntity = authRepository.findByUserTokenId(
          UUID.fromString(aboutUserRequest.getUserId()));
      if (userEntity.isEmpty()) {
        throw new AboutInfoException(String.format("Пользователя с таким user id %s не существует",
            aboutUserRequest.getUserId()));
      }

      AboutUserEntity aboutUser = new AboutUserEntity();
      aboutUser.setUser(userEntity.get());
      aboutUser.setText(aboutUserRequest.getAboutInfo());
      aboutUserRepository.save(aboutUser);
      return true;
  }
}
