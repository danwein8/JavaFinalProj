package com.example.javafinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogInDef implements Initializable {
    @FXML
    private Button LoginBT;
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private Label IncorrectLoginLabel;

    private Connection connection;
    private Database DatabaseConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection = new Database();
    }

    private void CheckLogin() throws SQLException {
        String username = Username.getText();
        String password = Password.getText();
        LogIn logIn = new LogIn();
        try {
            String Query = "Select * FROM users";
            //ResultSet resultSet = DatabaseConnection.InitializeDatabase().createStatement().executeQuery(Query);
            //initialize("jdbc:mysql://localhost/javafinalproject", resultSet);
            connection = DatabaseConnection.InitializeDatabase();
            ResultSet resultSet = connection.createStatement().executeQuery(Query);
            while (resultSet.next()) {
                if (resultSet.getString("Email").equals(username) && resultSet.getString("Password").equals(password)){
                    logIn.ChangeScene("Main.fxml");
                }
                else{
                    IncorrectLoginLabel.setText("Incorrect Username or Password");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void UserLogin(ActionEvent event) throws SQLException {
        CheckLogin();
    }


}
