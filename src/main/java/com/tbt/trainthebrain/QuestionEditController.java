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
    private TextField id;
    @FXML
    private TextArea questionText,answerText0,answerText1,answerText2,answerText3;
    @FXML
    private CheckBox answerCorrect0, answerCorrect1,answerCorrect2,answerCorrect3;

    public void cancelQuestionEditClick(ActionEvent actionEvent) {
        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    public void saveQuestionEditClick(ActionEvent actionEvent) {
        //TODO: handle DB Save Event

        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
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

        //TODO: Fülle die answers in einer besseren art in das form als so:
        answerText0.setText( answers.get(0).getText() );
        answerText1.setText( answers.get(1).getText() );
        /*
        answerText2.setText( answers.get(2).getText() );
        answerText3.setText( answers.get(3).getText() );

         */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Question edit Init called");
    }
}
