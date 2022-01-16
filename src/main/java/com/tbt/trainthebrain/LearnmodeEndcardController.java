package com.tbt.trainthebrain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LearnmodeEndcardController extends AppController implements Initializable {

    @FXML
    HBox summaryContent;

    @FXML
    Text percentCorrectText,userPointsText,maxPointsText;

    double countOfQuestions, maxPts, userPts;
    int fullyCorrectCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /* Custom Init für die Darstellung des Diagrams */
    public void initResults(ArrayList<QuestionResult> results) {

        countOfQuestions = results.size();
        for (QuestionResult qr: results) {
            maxPts += qr.countAnswers;
            userPts += qr.pts;
            if(qr.fullyCorrect) fullyCorrectCount++;
        }

        DecimalFormat df = new DecimalFormat("#.#");

        percentCorrectText.setText(df.format((100 / countOfQuestions) * fullyCorrectCount) +"%");
        userPointsText.setText(Double.toString(userPts));
        maxPointsText.setText(Double.toString(maxPts));

        int incorrectAnswered = (int) (countOfQuestions - fullyCorrectCount);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                                                        new PieChart.Data(fullyCorrectCount + " korrekt", fullyCorrectCount),
                                                        new PieChart.Data(incorrectAnswered + " falsch", (countOfQuestions - fullyCorrectCount))
                                                    );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Völlig korrekt beantwortet:");

        summaryContent.getChildren().add(chart);

    }
}
