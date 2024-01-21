package com.project.eneler.service.impl;

import com.project.eneler.model.dto.request.questions.ResultRequest;
import com.project.eneler.model.entity.ResultEntity;
import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.model.enums.Part;
import com.project.eneler.repository.AuthRepository;
import com.project.eneler.repository.ResultRepository;
import com.project.eneler.service.ResultService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

  private final ResultRepository resultRepository;
  private final AuthRepository authRepository;

  @Override
  public String sendResult(ResultRequest resultRequest) {
    try {
      Optional<UserEntity> entityOptional = authRepository.findByUserTokenId(
          UUID.fromString(resultRequest.getUserId()));
      if (entityOptional.isPresent()) {
        UserEntity entity = entityOptional.get();
        ResultEntity result = new ResultEntity();
        result.setUser(entity);
        result.setCount(Integer.valueOf(resultRequest.getCount()));
        result.setPart(Part.valueOf(resultRequest.getPart()).toString());
        resultRepository.save(result);
        return "Success!";
      }
      return "";
    } catch (Exception ex) {
      log.error(ex.toString());
      return "";
    }
  }
}
