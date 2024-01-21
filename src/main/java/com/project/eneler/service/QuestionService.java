package com.project.eneler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.eneler.model.dto.request.questions.QuestionCreateRequest;
import com.project.eneler.model.dto.request.questions.QuestionRequest;
import com.project.eneler.model.dto.response.QuestionResponse;
import com.project.eneler.model.entity.QuestionEntity;
import java.util.List;

public interface QuestionService {
  QuestionResponse findAll() throws JsonProcessingException;
  QuestionCreateRequest findById(Integer questionId);
  void deleteById(Integer questionId);

  boolean attachedTestOnUser(String userId, List<Integer> questionIds);
  boolean createQuestions(QuestionRequest questionRequest);
}
