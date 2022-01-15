package com.tbt.trainthebrain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LearnmodeEndcardController extends AppController implements Initializable {

    @FXML
    HBox summaryContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /* Custom Init für die Darstellung des Diagrams */
    public void initResults(int points, int maxPoints) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                                                        new PieChart.Data("korrekt", 13),
                                                        new PieChart.Data("falsch / nicht beantwortet", 25)
                                                    );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Erreichte Punktzahl");

        summaryContent.getChildren().add(chart);

    }
}
