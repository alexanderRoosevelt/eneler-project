package com.project.eneler.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.eneler.model.dto.request.questions.AnswerOption;
import com.project.eneler.model.dto.request.questions.QuestionCreateRequest;
import com.project.eneler.model.dto.request.questions.QuestionRequest;
import com.project.eneler.model.dto.response.QuestionResponse;
import com.project.eneler.model.entity.QuestionEntity;
import com.project.eneler.model.entity.UserEntity;
import com.project.eneler.model.entity.UsersQuestionsAttached;
import com.project.eneler.repository.AuthRepository;
import com.project.eneler.repository.QuestionRepository;
import com.project.eneler.repository.UsersQuestionsAttachedRepository;
import com.project.eneler.service.QuestionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final AuthRepository authRepository;
  private final UsersQuestionsAttachedRepository usersQuestionsAttachedRepository;

  @Override
  public QuestionResponse findAll() {
    try {

      List<QuestionEntity> questionEntities = questionRepository.findAll();
      List<QuestionCreateRequest> listQuestions = new ArrayList<>();

      if (!questionEntities.isEmpty()) {
        for (QuestionEntity entity : questionEntities) {
          QuestionCreateRequest question = new QuestionCreateRequest();
          question.setId(entity.getId().toString());
          question.setQuestionText(entity.getQuestionText());
          // Используем ObjectMapper для преобразования JSON в объект
          ObjectMapper objectMapper = new ObjectMapper();
          List<AnswerOption> answerOptions = parseAnswerOptions(entity.getOptions());
          question.setAnswerOptions(answerOptions);
          listQuestions.add(question);
        }
      }
      return QuestionResponse.builder()
          .questionList(listQuestions)
          .build();
    } catch (Exception ex) {
      log.error(ex.toString());
      return null;
    }
  }

  private static List<AnswerOption> parseAnswerOptions(String input) {
    List<AnswerOption> result = new ArrayList<>();

    Pattern pattern = Pattern.compile("AnswerOption\\(answer=(.*?), option=(.*?)\\)");
    Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      String answer = matcher.group(1).trim();
      boolean option = Boolean.parseBoolean(matcher.group(2).trim());

      result.add(AnswerOption.builder().answer(answer).option(option).build());
    }

    return result;
  }

  @Override
  public QuestionCreateRequest findById(Integer questionId) {
    try {

      Optional<QuestionEntity> question = questionRepository.findById(questionId);
      if (question.isPresent()) {
        QuestionEntity entity = question.get();
        QuestionCreateRequest result = new QuestionCreateRequest();
        result.setId(entity.getId().toString());
        result.setQuestionText(entity.getQuestionText());
        // Используем ObjectMapper для преобразования JSON в объект
        ObjectMapper objectMapper = new ObjectMapper();
        List<AnswerOption> answerOptions = parseAnswerOptions(entity.getOptions());
        result.setAnswerOptions(answerOptions);
        return result;
      }
      return null;
    }catch (Exception ex) {
      log.error(ex.toString());
      return null;
    }
  }

  @Override
  public void deleteById(Integer questionId) {
    questionRepository.deleteById(questionId);
  }

  @Override
  public boolean attachedTestOnUser(String userId, List<Integer> questionIds) {
    try {
      Optional<UserEntity> userEntityOptional = authRepository.findByUserTokenId(
          UUID.fromString(userId));
      List<UsersQuestionsAttached> datas = new ArrayList<>();
      if (userEntityOptional.isPresent()) {
        for (Integer id : questionIds) {
          UsersQuestionsAttached attached = new UsersQuestionsAttached();
          attached.setUserId(userEntityOptional.get().getId());
          attached.setQuestionId(id);
          datas.add(attached);
        }
        usersQuestionsAttachedRepository.saveAll(datas);
        return true;
      }
      return false;
    } catch (Exception ex) {
      log.error(ex.toString());
      return false;
    }
  }

  @Override
  // @Transactional
  public boolean createQuestions(QuestionRequest questionRequest) {
    try {

      List<QuestionEntity> questionEntities = new ArrayList<>();

      for (QuestionCreateRequest questionCreateRequest : questionRequest.getQuestionList()) {
        questionEntities.add(
            QuestionEntity.builder()
                .questionText(questionCreateRequest.getQuestionText())
                .options(questionCreateRequest.getAnswerOptions().toString())
                .build()
        );
      }
      questionRepository.saveAll(questionEntities);
      return true;
    } catch (Exception ex) {
      log.error(ex.toString());
      return false;
    }
  }
}
