package com.tbt.trainthebrain;

import com.tbt.trainthebrain.LayoutHelpers.ListItemHelper;
import com.tbt.trainthebrain.sqlcontroller.DBTasks;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionEditOverviewController extends AppController implements Initializable {
    @FXML
    VBox editListQuestionContainerOutter;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListItemHelper lih = new ListItemHelper();

        DBTasks con = new DBTasks();

        ArrayList<Question> questionsArray = con.getAllQuestionsFromDb();

        for (Question q: questionsArray) {
            editListQuestionContainerOutter.getChildren().add(lih.createSimpleListElementWithLink(q));
        }


    }
}
