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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            AppController sceneController = loader.getController();
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }

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
