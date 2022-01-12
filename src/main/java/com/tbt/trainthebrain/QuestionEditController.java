package com.tbt.trainthebrain;

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

    private ArrayList<TextArea> answerText = new ArrayList<>();

    private  ArrayList<TextField> answerIds = new ArrayList<>();

    public void cancelQuestionEditClick(ActionEvent actionEvent) {
        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    public void saveQuestionEditClick(ActionEvent actionEvent) {
        //TODO: handle DB Save Event

        //insert datatoDB if id == empty





/*
        for (int i = 0; i < answerText.size(); i++) {
            answers.add(answerText.get(i).getText());
        }*/


        int questionId = Integer.parseInt(id.getText());
        String question = questionText.getText();

      //  Question addDBQuestion = new Question(questionId, question,createNewAnswerObjects(questionId));



        //else update question id =  id.getText in int umwandeln
        //Update where id = question id

        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    public void editQuestionAnswer(){

        int questionId = Integer.parseInt(id.getText());
        String question = questionText.getText();

        //Question addDBQuestion = new Question(questionId, question,createNewAnswerObjects(questionId));


        //Wenn answer id 0 und Text ist leer gar nichts machen

        //wenn answer id 0 und Text hat länge = neue antwort in Db hinzufügen (insert mit question id und answerText)

        //Wenn answer id > 0 und Text hat länge = bestehende antwort updaten ( update mit where answerId = answerid )

        //wenn answer id > 0 und text ist leer = Delete answer where answerid = answerid

        String queryQuestion = "UPDATE tbl_questions SET question_text='" + question +  " WHERE question_id=" + questionId;

        //String queryAnswer = "UPDATE tbl_answers SET answer_text='" + question +  " WHERE answer_id=" +  ;

    }

    public void addNewQestionInDb(){
        //insert datatoDB if id == empty

//Wenn question id = 0 und text hat länge = insert  question text (wenn erzeugt brauche id von Question für Answers)

        int questionId = Integer.parseInt(id.getText());
        String question = questionText.getText();

        if (questionId == 0 && !question.isEmpty()){
            // insert in question table --> question_text
            //return generierte question id von DB --> LAST_INSERT_ID();

        }




    }

    public ArrayList<Answer> createNewAnswerObjects(int questionID){

        //erzeugt neue Answerobjekte befüllt sie mit answerId, text, isCorrect, question id von zugehöriger Frage
        //Füllt alle Answerojekte in eine Answer ArrayList

        int aId0 = Integer.parseInt(answerId0.getText());
        int aId1 = Integer.parseInt(answerId1.getText());
        int aId2 = Integer.parseInt(answerId2.getText());
        int aId3 = Integer.parseInt(answerId3.getText());

        String answerObjText0 = answerText0.getText();
        String answerObjText1 = answerText1.getText();
        String answerObjText2 = answerText2.getText();
        String answerObjText3 = answerText3.getText();

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
            answerText.get(i).setText(answers.get(i).getText());
            answerIds.get(i).setText(String.valueOf(answers.get(i).getId()));
        }
    }

    public void fillAnswerTextAndIdInArray(){
        //answerText und answerIds in ArrayList abgefüllt
        answerText.add(answerText0);
        answerText.add(answerText1);
        answerText.add(answerText2);
        answerText.add(answerText3);

        answerIds.add(answerId0);
        answerIds.add(answerId1);
        answerIds.add(answerId2);
        answerIds.add(answerId3);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Question edit Init called");
        fillAnswerTextAndIdInArray();
    }
}
