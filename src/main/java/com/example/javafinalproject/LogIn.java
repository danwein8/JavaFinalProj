package com.example.javafinalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LogIn extends Application {
    private static Stage stage;


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(LogIn.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setResizable(false);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeScene(String fxml) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(LogIn.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);*/
        Parent pane = FXMLLoader.load(LogIn.class.getResource(fxml));
        stage.getScene().setRoot(pane);
    }
}
