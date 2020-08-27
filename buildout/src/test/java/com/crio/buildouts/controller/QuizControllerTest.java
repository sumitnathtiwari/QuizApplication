package com.crio.buildouts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crio.buildouts.QuizApplication;
import com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.GetQuestionAndResponse;
import com.crio.buildouts.dto.GetQuestionList;
import com.crio.buildouts.repositoriesservices.QuizServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

@SpringBootTest(classes = QuizApplication.class)
//@WebMvcTest(value = QuizController.class)
public class QuizControllerTest {

  public static final String quizApi = "/quiz/v1";
  public static final String getQuestion = "/question";
  public static final String submitResponse = "/submit";

  private MockMvc mockMvc;
  
  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
  }


  @Mock
  private QuizServices quizservices;

  @InjectMocks
  private QuizController quizController;

  @Test
  public void getQuestionRequestTest() throws Exception {
    // testing the get request
    // build the request
    Mockito.when(quizservices.getQuestionServices())
        .thenReturn(new GetQuestionList(new ArrayList<GetQuestion>()));

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(quizApi + getQuestion)
        .accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    String expected = "{questions:[]}";

    assertEquals(200, result.getResponse().getStatus());

    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
  }

  public static String jsonToString(String path) throws IOException {
    File inputFile = ResourceUtils.getFile("classpath:" + path);
    return FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);
  }

  @Test
  public void postResponseRequestTest() throws Exception {

    String requestPath = "fixtures/sample_submit_question_request.json";
    String responsePath = "fixtures/sample_submit_question_response.json";

    String response = jsonToString(requestPath);
    String responseBody = jsonToString(responsePath);

    ObjectMapper mapper = new ObjectMapper();
    GetQuestionAndResponse value = mapper.readValue(responseBody, GetQuestionAndResponse.class);

    Mockito.when(quizservices.getResponse()).thenReturn(value);

    URI uri = new URI(quizApi + submitResponse);
    RequestBuilder postRequest = MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON).content(response);

    MvcResult result = mockMvc.perform(postRequest).andReturn();
    //verify request is ok
    assertEquals(200, result.getResponse().getStatus());
    JSONAssert.assertEquals(responseBody, result.getResponse().getContentAsString(), false);
  }
}