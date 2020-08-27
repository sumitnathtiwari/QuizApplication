
package com.crio.buildouts.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class QuestionTest {

  
  @Test
  public void serializedAndDeserializedGetQuestionJson() throws JSONException, IOException {

    // first create object mapper
    final String jsonString = "{\n" + "  \"questionId\": \"001\",\n"
        + "  \"title\": \"What is the default IP address of localhost?\",\n"
        + "  \"description\": \"General question\",\n" 
        + "  \"type\": \"objective-single\",\n" 
        + "  \"options\": {\n"
        + "  \"1\": \"0.0.0.0\",\n" 
        + "  \"2\": \"192.168.0.12\",\n" 
        + "  \"3\": \"127.0.0.1\",\n"
        + "  \"4\": \"255.255.255.255\"\n" + "}\n" 
        + "}";
    // create objectMapper and read using Question.java
    GetQuestion question = new GetQuestion();
    question = new ObjectMapper().readValue(jsonString, GetQuestion.class);

    String actualString = "";

    actualString = new ObjectMapper().writeValueAsString(question);

    JSONAssert.assertEquals(actualString, jsonString, true);

  }

}