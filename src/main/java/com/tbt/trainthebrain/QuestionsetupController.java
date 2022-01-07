package com.tbt.trainthebrain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionsetupController implements Initializable {
    @FXML
    VBox editListQuestionContainerOutter;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("init called");
        HBox questionElement = new HBox();
        questionElement.getStyleClass().add("editListQuestionContainer");
        questionElement.setPadding(new Insets(10));
        Text questionText = new Text("Eine einfache Frage?");
        questionText.getStyleClass().add("editListQuestionText");
        questionElement.getChildren().add(questionText);

        editListQuestionContainerOutter.getChildren().add(questionElement);

    }
}
