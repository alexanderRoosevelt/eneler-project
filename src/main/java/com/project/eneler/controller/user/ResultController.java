package com.project.eneler.controller.user;

import com.project.eneler.model.dto.request.questions.ResultRequest;
import com.project.eneler.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class ResultController {

  private final ResultService resultService;

  @PostMapping
  public ResponseEntity<String> sendResult(@RequestBody ResultRequest resultRequest) {
    return ResponseEntity.ok(resultService.sendResult(resultRequest));
  }
}
