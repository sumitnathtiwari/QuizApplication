package com.crio.buildouts.repositoriesservices;

import com.crio.buildouts.dto.GetQuestion;
import com.crio.buildouts.dto.GetQuestionAndResponse;
import com.crio.buildouts.dto.GetQuestionList;
import com.crio.buildouts.dto.GetResponse;
import com.crio.buildouts.dto.Question;
import com.crio.buildouts.dto.Summary;
import com.crio.buildouts.model.QuestionModel;
import com.crio.buildouts.repositories.QuestionRepositories;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServicesImpl implements QuizServices {

  @Autowired
  private QuestionRepositories questionRepositories;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public GetQuestionList getQuestionServices() {
    List<GetQuestion> getQuestion = modelMapper.map(questionRepositories.findAll(),
        new TypeToken<List<GetQuestion>>() {
        }.getType());
    GetQuestionList getQuestionList = new GetQuestionList(getQuestion);
    return getQuestionList;
  }

  @Override
  public void saveResponses(List<GetResponse> userResponse) {
    for (GetResponse response : userResponse) {
      Optional<QuestionModel> question = questionRepositories.findByQuestionId(
          response.getQuestionId());
      if (question.isPresent() == true) {
        QuestionModel questionModel = question.get();
        // update the user response
        questionModel.setUserAnswer(response.getUserResponse());

        // check if it match with user answer
        if (iscorrectAnswer(response.getUserResponse(),
            question.get().getCorrectAnswer()) == true) {
          questionModel.setAnswerCorrect(true);
        } else {
          questionModel.setAnswerCorrect(false);
        }
        questionRepositories.save(questionModel);
      }
    }
  }

  @Override
  public GetQuestionAndResponse getResponse() {
    GetQuestionAndResponse getQuestionAndResponse = new GetQuestionAndResponse();
    getQuestionAndResponse.setQuestions(modelMapper.map(
        questionRepositories.findAll(), new TypeToken<List<Question>>() {}.getType()));
    long total = questionRepositories.count();
    long score = questionRepositories.countByAnswerCorrect(true);
    Summary summary = new Summary(score, total);
    getQuestionAndResponse.setSummary(summary);
    return getQuestionAndResponse;
  }

  public static boolean iscorrectAnswer(List<String> userResponse, List<String> correctAnswer) {
    if (correctAnswer.equals(userResponse)) {
      return true;
    }
    return false;
  }

}