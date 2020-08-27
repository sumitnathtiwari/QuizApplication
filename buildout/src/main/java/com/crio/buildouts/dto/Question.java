package com.crio.buildouts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

  @NonNull
  private String questionId;
  private String title;
  private String description;
  private String type;
  private Map<String, String> options;
  private List<String> userAnswer;
  private List<String> correct;
  private String explanation;
  private Boolean answerCorrect;
}