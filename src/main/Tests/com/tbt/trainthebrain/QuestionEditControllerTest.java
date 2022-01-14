package com.tbt.trainthebrain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionEditControllerTest {


    @Test
    public void verifyMinOneAnswerIsTrue_shouldReturnTrue_FAKE(){
        QuestionEditController qec = new FakeQuestionEditController();
        boolean result = qec.verifyMinOneAnswerIsTrue();
        Assertions.assertTrue(result);

    }
    @Test
    public void verifyMinOneAnswerIsTrueForTestingFalse_shouldReturnFalse_FAKE(){
        FakeQuestionEditController fqec = new FakeQuestionEditController();
        boolean result = fqec.verifyMinOneAnswerIsTrueForTestingFalse();
        Assertions.assertFalse(result);


    }
    @Test
    public void verifyIfMinTwoAnswers_shouldReturnTrue_FAKE(){
        FakeQuestionEditController fqec = new FakeQuestionEditController();
        boolean result = fqec.verifyIfMinTwoAnswers();
        Assertions.assertTrue(result);
    }

    @Test
    public void verifyIfMinTwoAnswersForTestingFalse_shouldReturnFalse_FAKE(){
        FakeQuestionEditController fqec = new FakeQuestionEditController();
        boolean result = fqec.verifyIfMinTwoAnswersForTestingFalse();
        Assertions.assertFalse(result);

    }




}