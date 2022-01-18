package com.tbt.trainthebrain;

import com.tbt.trainthebrain.sqlcontroller.DBTasks;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class AppController {

    boolean statusDb;
    public ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestionsFromDatabase() {
        DBTasks con = new DBTasks();
        return con.getAllQuestionsFromDb();
    }

    public void backToMainClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        switchBasicScenes(stage,"main.fxml" );
    }

    public void switchToLearningModeClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        switchBasicScenes(stage,"learnmode-setup.fxml" );
    }

    public void switchToEditQuestionsClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        switchBasicScenes(stage,"question-edit-overview.fxml" );
    }

    public void addNewQuestionClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        switchBasicScenes(stage,"question-edit.fxml" );
    }

    private void switchBasicScenes(Stage stage, String fxmlName){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlName));
        try {
            Scene questioningScene = new Scene(loader.load());
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Szene konnte nicht geladen werden: " + fxmlName);
            ioe.printStackTrace();
        }
    }

    public void fadeInTransition(Node element, int duration, int delay){
        element.setOpacity(0);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(duration));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);
        fade.setAutoReverse(false);
        fade.setNode(element);
        if(delay > 0 ) {
            fade.setDelay(Duration.millis(delay));
        }
        fade.play();
    }



}
