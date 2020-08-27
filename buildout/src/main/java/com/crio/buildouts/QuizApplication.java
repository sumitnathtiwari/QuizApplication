package com.crio.buildouts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizApplication {

  public static void main(String[] args) {
    // start the spring boot quiz application
    SpringApplication.run(QuizApplication.class, args);
  }
}