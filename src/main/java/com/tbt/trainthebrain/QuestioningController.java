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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestioningController extends AppController implements Initializable {
    @FXML
    Button counterPlusBtn, counterMinusBtn, startBtn, backBtn;
    @FXML
    TextField questionsToPlayCounter;
    @FXML
    Text countOfQuestions;

    int countOfQuestionsInDB = 52;          // TODO: mit 0 initiieren


    public void startTrainingClicked(ActionEvent actionEvent) {
        //ToDo: Switch auf Scene (tbd) wo die Fragen gestellt werden
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


    
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // TextField listener um sicherzustellen das nur nummern eingetragen werden
        questionsToPlayCounter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*") || newValue.length() > 0 && (Integer.parseInt(newValue) > countOfQuestionsInDB || Integer.parseInt(newValue) < 1 )) {
                    questionsToPlayCounter.setText(oldValue);
                }

                if(questionsToPlayCounter.getText().length() > 0){
                    startBtn.setDisable(false);
                }else{
                    startBtn.setDisable(true);
                }
            }
        });

        // Anzahl der Fragen aus der Datenbank laden und im Text platzieren

        // Die Anzahl der Fragen werden in der Seite ausgegeben:
        countOfQuestions.setText(String.valueOf(countOfQuestionsInDB));
        questionsToPlayCounter.setText(String.valueOf(countOfQuestionsInDB));

        
    }

    public void counterChangeClicked(ActionEvent actionEvent) {
        int oldQCount = 0;

        if(questionsToPlayCounter.getText().length() > 0){
            oldQCount = Integer.parseInt(questionsToPlayCounter.getText());
        }

        // hole den Character im Button Text
        Character c = ((Button) actionEvent.getSource()).getText().charAt(0);
        // Prüfe ob es ein minus oder plus ist
        if(c == '-'){
            if(--oldQCount > 0){
                questionsToPlayCounter.setText(String.valueOf(oldQCount--));
            }
        }else if(c == '+'){
            if(++oldQCount <= countOfQuestionsInDB){
                questionsToPlayCounter.setText(String.valueOf(oldQCount++));
            }
        }

        // schreibe den neuen wert in das textfield

    }
}
