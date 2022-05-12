module com.example.javafinalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafinalproject to javafx.fxml;
    exports com.example.javafinalproject;
}