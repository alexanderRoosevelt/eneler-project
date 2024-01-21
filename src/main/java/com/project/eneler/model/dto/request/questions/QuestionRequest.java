package com.project.eneler.model.dto.request.questions;

import java.util.List;
import lombok.Data;

@Data
public class QuestionRequest {
  private List<QuestionCreateRequest> questionList;
}
