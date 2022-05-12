package com.example.javafinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CartDef implements Initializable {

    Product currentProduct;

    @FXML
    private ImageView ProductImage;

    @FXML
    private Label NameOfProduct;

    @FXML
    private Label PriceOfProduct;

    @FXML
    private Label TotalForItem;

    @FXML
    private TextField Quantity;

    private Connection connection;

    private Database DatabaseConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection = new Database();
    }

    public void recieveData(Product product) {
        currentProduct = product;
        NameOfProduct.setText(product.getName());
        PriceOfProduct.setText(Double.valueOf(product.getPrice()).toString());
        TotalForItem.setText(Double.valueOf(product.getPrice()* product.getQuantity()).toString());
    }

    public void setQuantity(ActionEvent actionEvent) {
        currentProduct.setQuantity(Integer.valueOf(Quantity.getText()));
        TotalForItem.setText(Double.valueOf(currentProduct.getPrice()* currentProduct.getQuantity()).toString());
    }
}
