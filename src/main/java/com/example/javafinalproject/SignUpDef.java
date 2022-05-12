package com.example.javafinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpDef implements Initializable {

    @FXML
    private TextField FirstNametf;

    @FXML
    private TextField LastNametf;

    @FXML
    private TextField EmailAddresstf;

    @FXML
    private TextField Passwordtf;

    @FXML
    private TextField ConfirmPasswordtf;

    @FXML
    private Label FirstNamelbl;

    @FXML
    private Label LastNamelbl;

    @FXML
    private Label EmailAddresslbl;

    @FXML
    private Label Passwordlbl;

    @FXML
    private Label ConfirmPasswordlbl;

    @FXML
    private Label PasswordsDoNotMatch;

    @FXML
    private Label EmailAddressIsUsedlbl;

    @FXML
    private Label FillAllFeilds;

    @FXML
    private Button SignupBT;

    private Connection connection;

    private Database DatabaseConnection;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection = new Database();
    }

    private void CheckSignin() throws SQLException {
        if (FirstNametf.getText().isEmpty() || LastNametf.getText().isEmpty() || EmailAddresstf.getText().isEmpty() || Passwordtf.getText().isEmpty() || ConfirmPasswordtf.getText().isEmpty()) {
            FillAllFeilds.setText("Please fill all fields");
        }
        else if (!Passwordtf.getText().equals(ConfirmPasswordtf.getText())) {
            PasswordsDoNotMatch.setText("Passwords do not match");
        }
        else {
            String FirstName = FirstNametf.getText();
            String LastName = LastNametf.getText();
            String FullName = FirstName + " " + LastName;
            String EmailAddress = EmailAddresstf.getText();
            String Password = Passwordtf.getText();
            SignUp signUp = new SignUp();
            try {
                String query = "SELECT * FROM users";
                connection = DatabaseConnection.InitializeDatabase();
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                //while (resultSet.next()) {
                    String queryUser = "INSERT INTO users (Name, Email, Password) VALUES ('" + FullName + "', '" + EmailAddress + "', '" + Password + "')";
                    connection.createStatement().executeUpdate(queryUser);
                //}
                SignUp.ChangeScene("Main.fxml");
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void SignUp(ActionEvent event) throws SQLException {
        CheckSignin();
    }
}
