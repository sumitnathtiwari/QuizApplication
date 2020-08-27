
package com.crio.buildouts.controller;

import com.crio.buildouts.dto.GetQuestionAndResponse;
import com.crio.buildouts.dto.GetQuestionList;
import com.crio.buildouts.dto.GetResponseList;
import com.crio.buildouts.model.QuestionModel;
import com.crio.buildouts.repositoriesservices.QuizServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

  // this wil be our controller class which will directly interact with client
  public static final String quizApi = "/quiz/v1";
  public static final String getQuestion = "/question";
  public static final String submitQuestionResponse = "/submit";
  public static final String getSubmitResponse = "/submit";

  @Autowired
  private QuizServices quizServices;

  @GetMapping(quizApi + getQuestion)
  public ResponseEntity<GetQuestionList> quizQuestion() {
    GetQuestionList getQuestionList = quizServices.getQuestionServices();
    return ResponseEntity.ok().body(getQuestionList);
  }

  @PostMapping(quizApi + submitQuestionResponse)
  public ResponseEntity<GetQuestionAndResponse> quizAnswer(@RequestBody GetResponseList response) {
    quizServices.saveResponses(response.getResponses());
    GetQuestionAndResponse getQuestionAndResponse = quizServices.getResponse();
    return ResponseEntity.ok().body(getQuestionAndResponse);
  }

  @PutMapping(quizApi + submitQuestionResponse)
  public ResponseEntity<String> quizPost(@RequestBody List<QuestionModel> quiz) {
    return ResponseEntity.ok().body(null);
  }
  
}