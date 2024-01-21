package com.project.eneler.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.eneler.model.dto.request.questions.QuestionCreateRequest;
import com.project.eneler.model.dto.response.AttachedTestReponse;
import com.project.eneler.model.dto.response.QuestionResponse;
import com.project.eneler.model.entity.QuestionEntity;
import com.project.eneler.service.QuestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tests")
public class TestingController {

  private final QuestionService questionService;

  @GetMapping
  public ResponseEntity<QuestionResponse> getAll() throws JsonProcessingException {
    return ResponseEntity.ok(questionService.findAll());
  }

  @GetMapping("/{questionId}")
  public ResponseEntity<QuestionCreateRequest> getById(@PathVariable Integer questionId) {
    return ResponseEntity.ok(questionService.findById(questionId));
  }

  @PostMapping("/attached")
  public ResponseEntity<Boolean> attached(@RequestBody AttachedTestReponse attachedTestReponse) {
    return ResponseEntity.ok(questionService.attachedTestOnUser(attachedTestReponse.getUserId(),
        attachedTestReponse.getQuestionsId()));
  }
}
