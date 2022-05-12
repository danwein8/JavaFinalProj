package com.example.javafinalproject;

import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection DataBaseLink;

    //connect to the database
    public Connection InitializeDatabase() throws SQLException, ClassNotFoundException {
        /*String DataBaseName = "javafinalproject";
        String DataBaseUser = "root";
        String DataBasePassword = "Lukeisapuppy87?";
        String DataBaseURL = "jdbc:mysql://localhost/" + DataBaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DataBaseLink = DriverManager.getConnection("jdbc:mysql://localhost/javafinalproject", "root", "Lukeisapuppy87");
            System.out.println("Database connected\n");

        }*/
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            DataBaseLink = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/javafinalproject", "root", "Lukeisapuppy87?");

            System.out.println("Database connected");
        }
        catch (Exception ex) {
            System.out.println("Error connecting to database\n");
            return null;
        }
        return DataBaseLink;
    }
}
