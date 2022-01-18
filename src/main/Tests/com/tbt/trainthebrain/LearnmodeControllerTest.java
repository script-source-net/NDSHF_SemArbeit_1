package com.tbt.trainthebrain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


class LearnmodeControllerTest {

    @Test
    public void calcQuestionPts_ShouldReturnCountAnswers_4Answers() {
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(true, false));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(4.0, qr.countAnswers);
    }

    @Test
    public void calcQuestionPts_ShouldReturnCountAnswers_3Answers() {
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(false, true));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(3.0, qr.countAnswers);
    }
    @Test
    public void calcQuestionPts_ShouldReturnCountAnswers_2Answers() {
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, true));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(2.0, qr.countAnswers);
    }

    @Test
    public void calcQuestionPts_shouldReturnCountCorrectAnswers_2CorrectAnswers() {
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(true, false));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(2.0, qr.countCorrectAnswers);
    }

    @Test
    public void calcQuestionPts_shouldReturnCountWrongAnswers_2WrongAnswers() {
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(true, false));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(2.0, qr.countWrongAnswers);
    }

    @Test
    public void calcQuestionPts_shouldReturnReachedPtsMinusPtsCorrectZero_0Pts(){
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(true, false));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(0.0, qr.pts);
    }

    @Test
    public void calcQuestionPts_shouldReturnMaxReachedPts_4Pts(){
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, true),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(true, true));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertEquals(4.0, qr.pts);
    }

    @Test
    public void calcQuestionPts_shouldReturnIfQuestionIsFullyCorrectOneTrueSelectedFalse_False(){
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, false),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(true, true));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertFalse(qr.fullyCorrect);
    }

    @Test
    public void calcQuestionPts_shouldReturnIfQuestionIsFullyCorrectOneFalseSelectedTrue_False(){
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(false, true),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(true, true));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertFalse(qr.fullyCorrect);
    }

    @Test
    public void calcQuestionPts_shouldReturnIfQuestionIsFullyCorrect_True(){
        LearnmodeController lmC = new LearnmodeController();
        ArrayList<AnswerBox> fakeBoxList = new ArrayList<>();

        Collections.addAll(fakeBoxList,
                new FakeAnswerBox(true, true),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(false, false),
                new FakeAnswerBox(true, true));

        QuestionResult qr = lmC.calcQuestionPts(fakeBoxList);
        Assertions.assertTrue(qr.fullyCorrect);
    }



}

