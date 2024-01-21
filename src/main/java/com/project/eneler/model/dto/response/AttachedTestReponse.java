package com.project.eneler.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class AttachedTestReponse {
  private String userId;
  private List<Integer> questionsId;
}
