package com.project.eneler.model.dto.response;

import com.project.eneler.model.dto.request.questions.QuestionCreateRequest;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponse {
  private List<QuestionCreateRequest> questionList;
}
