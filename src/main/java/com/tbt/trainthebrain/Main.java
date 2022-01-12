package com.tbt.trainthebrain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960.0, 700.0);
        stage.setTitle("TtB - Train the Brain");
//        stage.setMaxWidth(1280.0);
//        stage.setMaxHeight(1080.0);
        stage.setMinWidth(960.0);
        stage.setMinHeight(700.0);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}