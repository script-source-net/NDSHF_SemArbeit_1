package com.tbt.trainthebrain;

import com.tbt.trainthebrain.LayoutHelpers.ListItemHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionEditOverviewController implements Initializable {
    @FXML
    VBox editListQuestionContainerOutter;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("init called");

        // Use helper to create list item:
        // ***** Demo Object ToDo: Just temp REMOVE
        ListItemHelper lih = new ListItemHelper();
        ArrayList<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer(0,"Antwort A",false));
        answers.add(new Answer(0,"Antwort B",true));

        Question tmpQ = new Question(0,"Eine einfache Frage oder?", answers );
        // **** end of Demo Obj

        // Kann für For Loop aller Fragen aus DB genutzt werden damit die Fragen im GUI gelistet werden:
        editListQuestionContainerOutter.getChildren().add(lih.createSimpleListElementWithLink(tmpQ));

    }
}
