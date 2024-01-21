package com.project.eneler.model.dto.request.questions;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class QuestionCreateRequest {
  private String id;
  @JsonProperty("question_text")
  private String questionText;
  @JsonProperty("answer_options")
  private List<AnswerOption> answerOptions;
}
