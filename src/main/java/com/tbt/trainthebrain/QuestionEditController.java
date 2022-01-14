package com.tbt.trainthebrain;

import com.tbt.trainthebrain.sqlcontroller.DBTasks;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionEditController implements Initializable {

    @FXML
    private TextField id, answerId0, answerId1, answerId2, answerId3;
    @FXML
    private TextArea questionText,answerText0,answerText1,answerText2,answerText3;
    @FXML
    private CheckBox answerCorrect0, answerCorrect1,answerCorrect2,answerCorrect3;

    private ArrayList<TextArea> answerTextsArray = new ArrayList<>();

    private  ArrayList<TextField> answerIdsArray = new ArrayList<>();

    private ArrayList<CheckBox> isCorrectCheckBoxesArray = new ArrayList<>();

    public void cancelQuestionEditClick(ActionEvent actionEvent) {
        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    public void saveQuestionEditClick(ActionEvent actionEvent) {
        //TODO: handle DB Save Event

        addNewQestionInDb();

        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    public void addNewQestionInDb() {

        //Wenn question id = 0 und text hat länge = insert question text (wenn erzeugt brauche id von Question für Answers)
        int createdQuestionId = 0;
        int questionId = Integer.parseInt(id.getText());
        String question = questionText.getText().trim();

        DBTasks con = new DBTasks();

        if (questionId == 0 && !question.isEmpty()) {
            // insert in question table --> question_text
            //return generierte question id von DB --> LAST_INSERT_ID();
            createdQuestionId = con.insertNewQuestion(question);
            System.out.println("Neue QuestionID von DB = " + createdQuestionId);


            //Wenn Frage erstellt mit neuer Frage Id, die Antworten erstellen

           addAnswerFromCreatetQuestion(createdQuestionId);


        } else if (questionId > 0 && !question.isEmpty()) {
            //Question wird geupdatet und Answers werden geupdatet gelöscht oder neue hinzugefügt
            con.UpdateQuestion(questionId, question);
            editNewAnswer(questionId, createdQuestionId);
            System.out.println("QuestionId > 0 = Question Update in DB");

        } else if (questionId > 0 && question.isEmpty()) {
            //Question und dazugehörige answers werden gelöscht
            con.deleteQuestionAndAnswersInDb(questionId);
            System.out.println("QuestionId > 0 und text ist leer = Question löschen und dazugehörige Answer werden gelöscht in DB");
        }


    }

    public void editNewAnswer(int oldQuestionId, int newCreatedQuestionId) {
        // erstellt neue Answers zu den bereits erstellten Questions
        //ändert bestehende Answers
        //löscht bestehende Answers, wenn kein Text vorhanden ist.
        int aId0 = 0;
        int aId1 = 0;
        int aId2 = 0;
        int aId3 = 0;


        //Check wenn AnswerId Feld nicht leer ist, dann lese aus und parse in Integer.
        if (!answerId0.getText().trim().isEmpty()) {
            aId0 = Integer.parseInt(answerId0.getText());
        }
        if (!answerId1.getText().trim().isEmpty()) {
            aId1 = Integer.parseInt(answerId1.getText());
        }
        if (!answerId2.getText().trim().isEmpty()) {
            aId2 = Integer.parseInt(answerId2.getText());
        }
        if (!answerId3.getText().trim().isEmpty()) {
            aId3 = Integer.parseInt(answerId3.getText());
        }

        //.trim() verhindert das Leerschläge als Inhalt erkannt werden
        String answer0 = answerText0.getText().trim();
        String answer1 = answerText1.getText().trim();
        String answer2 = answerText2.getText().trim();
        String answer3 = answerText3.getText().trim();


        ArrayList<Answer> updateAnswers = createNewAnswerObjects(oldQuestionId);
        //Question addDBQuestion = new Question(questionId, question,createNewAnswerObjects(questionId));

        DBTasks con = new DBTasks();
        //Wenn answer id 0 und Text ist leer gar nichts machen

        //wenn answer id 0 und Text hat länge = neue antwort in Db hinzufügen (insert mit question id und answerText)
        //Wenn verifytrue ist, dann kontrollier ob Antworten id = 0 und Text vorhanden --> Create new Answer Insert in DB

        if (verifyIfMinTwoAnswers()) {

            if (verifyMinOneAnswerIsTrue()) {


                if (aId0 == 0 && !answer0.isEmpty()) {
                    // erstelle neue Antwort mit bestehender questionId
                    System.out.println("eingelesene Id = " + aId0);
                    con.addOneAnswerInDB(updateAnswers.get(0));
                    System.out.println(updateAnswers.get(0));

                }
                if (aId0 > 0 && !answer0.isEmpty()) {
                    //Update Antwort
                    System.out.println("eingelesene Id = " + aId0);
                    con.updateAnswers(updateAnswers.get(0).getQuestionId(), updateAnswers.get(0).getText(), updateAnswers.get(0).getIsCorrect(), updateAnswers.get(0).getId());
                    System.out.println(updateAnswers.get(0));

                }
                if (aId0 > 0 && answer0.isEmpty()) {
                    //lösche Antwort
                    con.deleteAnswerInDb(updateAnswers.get(0).getQuestionId(),updateAnswers.get(0).getId());

                }

                if (aId1 == 0 && !answer1.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId1);
                    con.addOneAnswerInDB(updateAnswers.get(1));
                    System.out.println(updateAnswers.get(1));

                }
                if (aId1 > 0 && !answer1.isEmpty()) {
                    //Update Antwort
                    System.out.println("eingelesene Id = " + aId1);
                    con.updateAnswers(updateAnswers.get(1).getQuestionId(), updateAnswers.get(1).getText(), updateAnswers.get(1).getIsCorrect(), updateAnswers.get(1).getId());
                    System.out.println(updateAnswers.get(1));
                }
                if (aId1 > 0 && answer1.isEmpty()) {
                    //lösche Antwort
                    con.deleteAnswerInDb(updateAnswers.get(1).getQuestionId(), updateAnswers.get(1).getId());
                }

                if (aId2 == 0 && !answer2.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId2);
                    con.addOneAnswerInDB(updateAnswers.get(2));
                    System.out.println(updateAnswers.get(2));

                }
                if (aId2 > 0 && !answer2.isEmpty()) {
                    //Update Antwort
                    System.out.println("eingelesene Id = " + aId2);
                    con.updateAnswers(updateAnswers.get(2).getQuestionId(), updateAnswers.get(2).getText(), updateAnswers.get(2).getIsCorrect(), updateAnswers.get(2).getId());
                    System.out.println(updateAnswers.get(2));
                }
                if (aId2 > 0 && answer2.isEmpty()){
                    //lösche Antwort
                    con.deleteAnswerInDb(updateAnswers.get(2).getQuestionId(), updateAnswers.get(2).getId());
                }

                if (aId3 == 0 && !answer3.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId3);
                    con.addOneAnswerInDB(updateAnswers.get(3));
                    System.out.println(updateAnswers.get(3));
                }
                if (aId3 > 0 && !answer3.isEmpty()) {
                    //Update Antwort
                    System.out.println("eingelesene Id = " + aId3);
                    con.updateAnswers(updateAnswers.get(3).getQuestionId(), updateAnswers.get(3).getText(), updateAnswers.get(3).getIsCorrect(), updateAnswers.get(3).getId());
                    System.out.println(updateAnswers.get(3));

                }
                if (aId3 > 0 && answer3.isEmpty()){
                    //lösche Antwort
                    con.deleteAnswerInDb(updateAnswers.get(3).getQuestionId(), updateAnswers.get(3).getId());
                }


            } else {
                System.out.println("Mindestens eine Antwort muss als korrekt markiert sein");
            }
        } else {
            System.out.println("Mindestens 2 Antworten müssen eingetragen werden!");
        }

    }

    public void addAnswerFromCreatetQuestion(int newCreatetQuestionId){
        // Macht answers für neu erstellte Fragen mit neu erstellter questionid
        //Diese Methode wird nur ausgeführt wenn neue question erstellt wird.
        int aId0 = 0;
        int aId1 = 0;
        int aId2 = 0;
        int aId3 = 0;


        //Check wenn AnswerId Feld nicht leer ist, dann lese aus und parse in Integer.
        if (!answerId0.getText().trim().isEmpty()) {
            aId0 = Integer.parseInt(answerId0.getText());
        }
        if (!answerId1.getText().trim().isEmpty()) {
            aId1 = Integer.parseInt(answerId1.getText());
        }
        if (!answerId2.getText().trim().isEmpty()) {
            aId2 = Integer.parseInt(answerId2.getText());
        }
        if (!answerId3.getText().trim().isEmpty()) {
            aId3 = Integer.parseInt(answerId3.getText());
        }


        String answer0 = answerText0.getText().trim();
        String answer1 = answerText1.getText().trim();
        String answer2 = answerText2.getText().trim();
        String answer3 = answerText3.getText().trim();

// array von Answers mit answerObjekten, die questionId enthält von neu erstellter Question
        ArrayList<Answer> addAnswers = createNewAnswerObjects(newCreatetQuestionId);

        DBTasks con = new DBTasks();

        //wenn answer id 0 und Text hat länge = neue antwort in Db hinzufügen (insert mit question id und answerText)
        //Wenn verifytrue ist, dann kontrollier ob Antworten id = 0 und Text vorhanden --> Create new Answer Insert in DB

        if (verifyIfMinTwoAnswers()) {

            if (verifyMinOneAnswerIsTrue()) {


                if (aId0 == 0 && !answer0.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId0);
                    con.addOneAnswerInDB(addAnswers.get(0));
                    System.out.println(addAnswers.get(0));

                }



                if (aId1 == 0 && !answer1.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId1);
                    con.addOneAnswerInDB(addAnswers.get(1));
                    System.out.println(addAnswers.get(1));

                }


                if (aId2 == 0 && !answer2.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId2);
                    con.addOneAnswerInDB(addAnswers.get(2));
                    System.out.println(addAnswers.get(2));

                }


                if (aId3 == 0 && !answer3.isEmpty()) {
                    // erstelle neue Antwort
                    System.out.println("eingelesene Id = " + aId3);
                    con.addOneAnswerInDB(addAnswers.get(3));
                    System.out.println(addAnswers.get(3));
                }



            } else {
                System.out.println("Mindestens eine Antwort muss als korrekt markiert sein");



            }
        } else {
            System.out.println("Mindestens 2 Antworten müssen eingetragen werden!");

        }

    }

    // Verifiziert ob mindestens eine Antwort als korrekt markiert wurde
    public boolean verifyMinOneAnswerIsTrue() {
        boolean verify = false;
        boolean correctAnswer0 = answerCorrect0.isSelected();
        boolean correctAnswer1 = answerCorrect1.isSelected();
        boolean correctAnswer2 = answerCorrect2.isSelected();
        boolean correctAnswer3 = answerCorrect3.isSelected();

        if (!correctAnswer0 && !correctAnswer1 && !correctAnswer2 && !correctAnswer3) {
            verify = false;
            System.out.println("Alle Antworten sind not_correct");

        } else {
            verify = true;
            System.out.println("Mindestens eine Antwort is_correct");
        }
        return verify;

    }

    //Verifiziert ob mindestens 2 Antworten ausgefüllt wurden (Alle mögliche Kombinationen getestet)
    public boolean verifyIfMinTwoAnswers() {
        boolean verify = false;
        int answersCount = 0;
        /* Check mit for Schleife und möglichem Abbruch */
        for (int i = 0; i < 3; i++) {
            if(!answerTextsArray.get(i).getText().trim().isEmpty()){
                answersCount++;
            }
            if(answersCount == 2){
                verify = true;
                break;
            }
        }

        return verify;
    }



    public ArrayList<Answer> createNewAnswerObjects(int questionID){

        //erzeugt neue Answerobjekte befüllt sie mit answerId, text, isCorrect, question id von zugehöriger Frage
        //Füllt alle Answerojekte in eine Answer ArrayList

        int aId0 = 0;
        int aId1 = 0;
        int aId2 = 0;
        int aId3 = 0;


        //Check wenn AnswerId Feld nicht leer ist, dann lese aus und parse in Integer.
        if (!answerId0.getText().trim().isEmpty()) {
            aId0 = Integer.parseInt(answerId0.getText());
        }
        if (!answerId1.getText().trim().isEmpty()) {
            aId1 = Integer.parseInt(answerId1.getText());
        }
        if (!answerId2.getText().trim().isEmpty()) {
            aId2 = Integer.parseInt(answerId2.getText());
        }
        if (!answerId3.getText().trim().isEmpty()) {
            aId3 = Integer.parseInt(answerId3.getText());
        }

        String answerObjText0 = answerText0.getText().trim();
        String answerObjText1 = answerText1.getText().trim();
        String answerObjText2 = answerText2.getText().trim();
        String answerObjText3 = answerText3.getText().trim();

        boolean correctAnswer0 = answerCorrect0.isSelected();
        boolean correctAnswer1 = answerCorrect1.isSelected();
        boolean correctAnswer2 = answerCorrect2.isSelected();
        boolean correctAnswer3 = answerCorrect3.isSelected();


        Answer addDBAnswer0 = new Answer(aId0, answerObjText0, correctAnswer0, questionID);
        Answer addDBAnswer1 = new Answer(aId1, answerObjText1, correctAnswer1, questionID);
        Answer addDBAnswer2 = new Answer(aId2, answerObjText2, correctAnswer2, questionID);
        Answer addDBAnswer3 = new Answer(aId3, answerObjText3, correctAnswer3, questionID);

        ArrayList<Answer> answerAddList = new ArrayList<>();
        answerAddList.add(addDBAnswer0);
        answerAddList.add(addDBAnswer1);
        answerAddList.add(addDBAnswer2);
        answerAddList.add(addDBAnswer3);

        return answerAddList;

    }

    public void switchToEditQuestionsClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("question-edit-overview.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            QuestionEditOverviewController sceneController = loader.getController();
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }

    public void initWithData(Question question){
        id.setText(Integer.toString(question.getId()));
        questionText.setText(question.getQuestion());
        ArrayList<Answer> answers = question.getAnswers();


        //answers werden den answerText zugeteilt, wenn nur 3 answers bleibt 4. textarea einfach leer.
        for (int i = 0; i < answers.size(); i++) {
            answerTextsArray.get(i).setText(answers.get(i).getText());
            answerIdsArray.get(i).setText(String.valueOf(answers.get(i).getId()));
            isCorrectCheckBoxesArray.get(i).setSelected(answers.get(i).getIsCorrect());
        }
    }

    public void fillAnswerTextAndAnswerIdsCheckboxesInArray() {
        //AnswerText in ArrayList abgefüllt
        //AnswerId's in ArrayList abgefüllt
        //Checkboxes in ArrayList abgefüllt
        answerTextsArray.add(answerText0);
        answerTextsArray.add(answerText1);
        answerTextsArray.add(answerText2);
        answerTextsArray.add(answerText3);

        answerIdsArray.add(answerId0);
        answerIdsArray.add(answerId1);
        answerIdsArray.add(answerId2);
        answerIdsArray.add(answerId3);

        isCorrectCheckBoxesArray.add(answerCorrect0);
        isCorrectCheckBoxesArray.add(answerCorrect1);
        isCorrectCheckBoxesArray.add(answerCorrect2);
        isCorrectCheckBoxesArray.add(answerCorrect3);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Question edit Init called");
       fillAnswerTextAndAnswerIdsCheckboxesInArray();

        // **** end of Demo Obj

        /*initWithData(tmpQ);

        DBConnection con = new DBConnection();
        initWithData(con.getQuestionWithIdFromDB(32));*/
    }
}
