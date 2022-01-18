package com.tbt.trainthebrain;

import com.tbt.trainthebrain.LayoutHelpers.ListItemHelper;
import com.tbt.trainthebrain.sqlcontroller.DBTasks;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionEditOverviewController extends AppController implements Initializable {
    @FXML
    VBox editListQuestionContainerOutter, emptyNotificationContainer;

    @FXML
    Button deleteAllQuestionsBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateView();
    }

    private void updateView(){
        deleteAllQuestionsBtn.setDisable(true);
        editListQuestionContainerOutter.getChildren().clear();
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
            deleteAllQuestionsBtn.setDisable(false);
        }
    }

    public void trashAllClicked(ActionEvent actionEvent) {

        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
        alert.initOwner(stage);
        alert.setTitle("Sicher löschen?");
        alert.setHeaderText("Sollen alle Fragen gelöscht werden?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES)
        {
            DBTasks.deleteAllQuestions();
            emptyNotificationContainer.setVisible(true);
            updateView();
        }

    }
}
