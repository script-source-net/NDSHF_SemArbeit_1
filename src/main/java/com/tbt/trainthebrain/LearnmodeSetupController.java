package com.tbt.trainthebrain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LearnmodeSetupController extends AppController implements Initializable {
    @FXML
    Button counterPlusBtn, counterMinusBtn, startBtn, backBtn;
    @FXML
    TextField questionsToPlayCounter;
    @FXML
    Text countOfQuestions, howManyQuestionsText;
    @FXML
    VBox learnModeInfoContainer;
    @FXML
    HBox questionSetupOptionsContainer;

    int countOfQuestionsInDB = 3;          // TODO: mit 0 initiieren
    int questionsCounter = 0;




    public void startTrainingClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("learnmode.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            LearnmodeController sceneController = loader.getController();
            sceneController.initLearnmode(questionsCounter);
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }

    public void switchToEditOverviewClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("question-edit-overview.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            QuestionEditOverviewController sceneController = loader.getController();        // TODO: QuestionEditOverviewController soll AppController extenden damit man einheitlichen Code schreiben kann
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }


    
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // TextField listener um sicherzustellen das nur nummern eingetragen werden
        questionsToPlayCounter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*") || newValue.length() > 0 && (Integer.parseInt(newValue) > countOfQuestionsInDB || Integer.parseInt(newValue) < 1 )) {
                    questionsToPlayCounter.setText(oldValue);
                    questionsCounter = Integer.parseInt(oldValue);
                }

                if(questionsToPlayCounter.getText().length() > 0){
                    startBtn.setDisable(false);
                }else{
                    startBtn.setDisable(true);
                }
                // Anzahl der Fragen wird aktualisiert
                questionsCounter = Integer.parseInt(newValue);
            }
        });

        // Anzahl der Fragen aus der Datenbank laden und im Text platzieren


        // Die Anzahl der Fragen werden in der Seite ausgegeben UND für die übergabe in die nächste Scene übernommen:
        countOfQuestions.setText(String.valueOf(countOfQuestionsInDB));
        questionsToPlayCounter.setText(String.valueOf(countOfQuestionsInDB));
        questionsCounter = countOfQuestionsInDB;

        // Text anpassen wenn keine Fragen eingetragen sind:
        if(countOfQuestionsInDB == 0){
            // Kontrollen zum Start ausblenden:
            questionSetupOptionsContainer.setVisible(false);
            // Button generieren für Switch To
            Button switchToEditBtn = new Button("Jetzt Fragen eintragen");
            switchToEditBtn.getStyleClass().addAll("btn", "btn-default", "large");
            switchToEditBtn.addEventHandler(ActionEvent.ACTION, e -> {
                switchToEditOverviewClicked(e);
            });
            // Text anpassen
            howManyQuestionsText.setWrappingWidth(500.0);
            howManyQuestionsText.setTextAlignment(TextAlignment.CENTER);
            howManyQuestionsText.setText("Keine Fragen in der Datenbank gespeichert lege jetzt neue Fragen an um den Lernmodus starten zu können.");
            howManyQuestionsText.getStyleClass().add("text-error");
            learnModeInfoContainer.getChildren().add(switchToEditBtn);
        }else if(countOfQuestionsInDB < 5){
            // Text anpassen
            howManyQuestionsText.setText("Es sind nur sehr wenige Fragen in der Datenbank gespeichert, lege jetzt neue Fragen an.\nWie viele Fragen möchtest du spielen?");
            howManyQuestionsText.getStyleClass().add("text-warning");
            howManyQuestionsText.setTextAlignment(TextAlignment.CENTER);

        }

        
    }

    public void counterChangeClicked(ActionEvent actionEvent) {
        int oldQCount = 0;

        if(questionsToPlayCounter.getText().length() > 0){
            oldQCount = Integer.parseInt(questionsToPlayCounter.getText());
        }

        // hole den Character im Button Text
        Character c = ((Button) actionEvent.getSource()).getText().charAt(0);
        // Prüfe ob der char ein minus oder plus char ist
        // schreibe den neuen wert in das textfield
        if(c == '-' && --oldQCount > 0){
            questionsToPlayCounter.setText(String.valueOf(oldQCount--));
        }else if(c == '+' && ++oldQCount <= countOfQuestionsInDB){
            questionsToPlayCounter.setText(String.valueOf(oldQCount++));
        }

    }

}
