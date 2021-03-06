package com.crio.buildouts.integ.test.configs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentConfig {

  @NonNull
  private String name;

  private String url;

  @NonNull
  private String method;

  private String input;

  private int status;

  private String verification;

  private String response;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getVerification() {
    return verification;
  }

  public void setVerification(String verification) {
    this.verification = verification;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }
}
