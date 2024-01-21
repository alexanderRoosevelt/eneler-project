package com.project.eneler.model.dto.request.questions;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AnswerOption {
  private String answer;
  private boolean option;
}
