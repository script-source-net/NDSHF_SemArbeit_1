package com.tbt.trainthebrain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionEditControllerTest {


   @Test
    public void checkMinOneAnswerIsTrue_OneAnswerIsTrueReturnTrue(){
       QuestionEditController qec = new QuestionEditController();

       ArrayList<Answer> answers = new ArrayList<>();
       //Manuelles setzen von Correct oder leerer Konstruktor ändern mit boolean isCorrect?
       Answer answerC = new Answer();
       answerC.setCorrect(true);

       Answer answerNC = new Answer();
       answerNC.setCorrect(false);

       answers.add(answerC);
       answers.add(answerNC);

       boolean result = qec.checkMinOneAnswerIsTrue(answers);
       Assertions.assertTrue(result);
   }

   @Test
   public void checkMinOneAnswerIsTrue_NoAnswerIsTrueReturnFalse(){
      QuestionEditController qec = new QuestionEditController();

      ArrayList<Answer> answers = new ArrayList<>();
      Answer answerC = new Answer();
      answerC.setCorrect(false);

      Answer answerNC = new Answer();
      answerNC.setCorrect(false);

      answers.add(answerC);
      answers.add(answerNC);

      boolean result = qec.checkMinOneAnswerIsTrue(answers);
      Assertions.assertFalse(result);
   }




}