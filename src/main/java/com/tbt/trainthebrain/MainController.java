package com.tbt.trainthebrain;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController extends AppController{

    public void switchToLearningModeClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("learnmode-setup.fxml"));

        try {
            Scene newscene = new Scene(loader.load());
            AppController sceneController = loader.getController();
            stage.setScene(newscene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }

    public void switchToEditQuestionsClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("question-edit-overview.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            QuestionEditOverviewController sceneController = loader.getController();        // TODO: QuestionEditOverviewController has to extend AppController
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }
}