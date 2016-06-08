package com.argonnet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GraphiXApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View/MainView.fxml"));
        primaryStage.setTitle("GraphiX");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


}