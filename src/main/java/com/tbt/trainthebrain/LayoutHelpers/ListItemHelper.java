package com.tbt.trainthebrain.LayoutHelpers;

import com.tbt.trainthebrain.Main;
import com.tbt.trainthebrain.Question;

import com.tbt.trainthebrain.QuestionEditController;
import com.tbt.trainthebrain.QuestionEditOverviewController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ListItemHelper {
    /**
     *
     * @param question
     * @return styled HBox including Questiontext for Listings including Link to EditView
     */
    public HBox createSimpleListElementWithLink(Question question){
        // Erstelle HBox als Basis:
        HBox questionElement = new HBox();
        // Binde die CSS Klasse an:
        questionElement.getStyleClass().add("editListQuestionContainer");
        // Setze das Padding:
        questionElement.setPadding(new Insets(10));
        // Erstelle den Frage-Text als neues Text Element:
        Text questionText = new Text(question.getQuestion());
        // Binde die CSS Klasse an:
        questionText.getStyleClass().add("editListQuestionText");
        // Setze den Text als Child der HBox
        questionElement.getChildren().add(questionText);

        questionElement.setOnMouseClicked(e -> switchToEditQMaskClick(e, question));

        return questionElement;
    }

    public void switchToEditQMaskClick(MouseEvent mouseEvent, Question question) {
        System.out.println("switchToEditMaskClicked");
        Stage stage = (Stage) ((Node) mouseEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("question-edit.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            // Load Controller from FXML
            QuestionEditController sceneController = loader.getController();
            // Set given Object as field values
            sceneController.initWithData(question);

            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }
}
