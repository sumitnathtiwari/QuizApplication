package com.crio.buildouts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetQuestion {

  @NonNull
  private String questionId = "";
  private String title;
  private String description;
  private String type;
  private Map<String, String> options;
}