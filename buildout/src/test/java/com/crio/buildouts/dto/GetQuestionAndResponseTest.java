package com.crio.buildouts.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.util.ResourceUtils;

public class GetQuestionAndResponseTest {

  @Test
  public void serializedAndDeserializedGetQuestionAndResponseJson()
      throws JSONException, IOException, URISyntaxException {

    String path = "fixtures/sample_submit_question_response.json";
    File inputFile = ResourceUtils.getFile("classpath:" + path);
    final String jsonString = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);

    // create objectMapper and read using Question.java
    GetQuestionAndResponse question = new GetQuestionAndResponse();
    question = new ObjectMapper().readValue(jsonString, GetQuestionAndResponse.class);

    String actualString = "";
    actualString = new ObjectMapper().writeValueAsString(question);

    JSONAssert.assertEquals(actualString, jsonString, false);

  }

}