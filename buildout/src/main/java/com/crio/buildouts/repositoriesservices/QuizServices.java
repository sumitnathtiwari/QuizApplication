package com.crio.buildouts.repositoriesservices;

import com.crio.buildouts.dto.GetQuestionAndResponse;
import com.crio.buildouts.dto.GetQuestionList;
import com.crio.buildouts.dto.GetResponse;

import java.util.List;

public interface QuizServices {

  GetQuestionList getQuestionServices();

  void saveResponses(List<GetResponse> userResponse);

  GetQuestionAndResponse getResponse();
}