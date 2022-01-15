package com.tbt.trainthebrain;

import com.tbt.trainthebrain.LayoutHelpers.ListItemHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionEditOverviewController extends AppController implements Initializable {
    @FXML
    VBox editListQuestionContainerOutter, emptyNotificationContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questions = getQuestionsFromDatabase();
        setQuestionsIntoList();
    }

    private void setQuestionsIntoList(){
        System.out.println("Anzahl Fragen im questions obj:" + questions.size());
        ListItemHelper lih = new ListItemHelper();
        for (Question q: questions) {
            editListQuestionContainerOutter.getChildren().add(lih.createSimpleListElementWithLink(q));
        }
        /* Liste ersetzen durch Icon & Message wenn Liste leer ist */
        if(questions.size() > 0){
            emptyNotificationContainer.setVisible(false);
        }
    }

    public void addNewQuestionClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("question-edit.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            // Load Controller from FXML
            QuestionEditController sceneController = loader.getController();

            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }
}
