package com.project.eneler.model.enums;

public enum Part {
  CHOISE("CHOISE"),
  CHECKBOX("CHECKBOX"),
  ONE_ANSWER("ONE_ANSWER");

  private final String value;

  Part(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
