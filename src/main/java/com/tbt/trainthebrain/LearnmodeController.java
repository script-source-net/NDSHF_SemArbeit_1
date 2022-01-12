package com.tbt.trainthebrain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LearnmodeController extends AppController implements Initializable {

    @FXML
    Button cancelLearnModeBtn;

    @FXML
    Text actualQuestionNum,questionCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        actualQuestionNum.setText("0");
        questionCount.setText("52");

    }



}
