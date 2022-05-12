package com.example.javafinalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Cart extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Cart.class.getResource("Cart.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setResizable(false);
        stage.setTitle("Cart");
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Cart.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}
