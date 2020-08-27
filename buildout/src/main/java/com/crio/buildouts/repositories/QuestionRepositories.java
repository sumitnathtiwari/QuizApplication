package com.crio.buildouts.repositories;

import com.crio.buildouts.model.QuestionModel;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepositories extends MongoRepository<QuestionModel, String> {

  Optional<QuestionModel> findByQuestionId(String questionId);

  long countByAnswerCorrect(boolean b);

}