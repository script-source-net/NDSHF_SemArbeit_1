package com.tbt.trainthebrain;

import java.util.ArrayList;

public class FakeQuestionEditController extends QuestionEditController {


    @Override
    public boolean verifyMinOneAnswerIsTrue() {
        //Fake gibt true zurück, weil mindestens 1 Answer is true
        boolean verify = false;
        boolean correctAnswer0 = true;
        boolean correctAnswer1 = false;
        boolean correctAnswer2 = false;
        boolean correctAnswer3 = false;

        if (!correctAnswer0 && !correctAnswer1 && !correctAnswer2 && !correctAnswer3) {
            verify = false;
            System.out.println("Alle Antworten sind not_correct");

        } else {
            verify = true;
            System.out.println("Mindestens eine Antwort is_correct");
        }
        return verify;
    }

    public boolean verifyMinOneAnswerIsTrueForTestingFalse() {
        //FakeMethode gibt false zurück, weil alle Answers sind false
        boolean verify = false;
        boolean correctAnswer0 = false;
        boolean correctAnswer1 = false;
        boolean correctAnswer2 = false;
        boolean correctAnswer3 = false;

        if (!correctAnswer0 && !correctAnswer1 && !correctAnswer2 && !correctAnswer3) {
            verify = false;
            System.out.println("Alle Antworten sind not_correct");

        } else {
            verify = true;
            System.out.println("Mindestens eine Antwort is_correct");
        }
        return verify;
    }

    @Override
    public boolean verifyIfMinTwoAnswers() {
        //Testmethode mit 2 Antworten
        ArrayList<String> answerTextsArray = new ArrayList<>();
        answerTextsArray.add("Answer1");
        answerTextsArray.add("Answer2");
        answerTextsArray.add("");
        answerTextsArray.add("");
        boolean verify = false;
        int answersCount = 0;
        /* Check mit for Schleife und möglichem Abbruch */
        for (int i = 0; i < 3; i++) {
            if (!answerTextsArray.get(i).trim().isEmpty()) {
                answersCount++;
            }
            if (answersCount == 2) {
                verify = true;
                break;
            }
        }
        return verify;
    }

    public boolean verifyIfMinTwoAnswersForTestingFalse() {
        //Test methode mit einer Antwort
        ArrayList<String> answerTextsArray = new ArrayList<>();
        answerTextsArray.add("Answer1");
        answerTextsArray.add("");
        answerTextsArray.add("");
        answerTextsArray.add("");
        boolean verify = false;
        int answersCount = 0;
        /* Check mit for Schleife und möglichem Abbruch */
        for (int i = 0; i < 3; i++) {
            if (!answerTextsArray.get(i).trim().isEmpty()) {
                answersCount++;
            }
            if (answersCount == 2) {
                verify = true;
                break;
            }
        }
        return verify;
    }

}
