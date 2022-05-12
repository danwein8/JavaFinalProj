package com.example.javafinalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class SignUp extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(SignUp.class.getResource("SignupPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setResizable(false);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    public static void ChangeScene(String fxml) throws IOException {
        //FXMLLoader loader = new FXMLLoader(SignUp.class.getResource(fxml));
        //Scene scene = new Scene(loader.load());
        Parent pane = FXMLLoader.load(SignUp.class.getResource(fxml));
        stage.getScene().setRoot(pane);
    }

}

