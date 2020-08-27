package com.crio.buildouts.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "question")
@NoArgsConstructor
public class QuestionModel {

  @Id
  private String id;

  @NonNull
  private String questionId = "";

  private String title;

  private String description;

  private String type;

  private Map<String, String> options;

  @NonNull
  private List<String> correctAnswer = new ArrayList<String>();

  private List<String> userAnswer;

  private String explanation;

  private boolean answerCorrect;
}