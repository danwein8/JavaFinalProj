package com.example.javafinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemPageDef implements Initializable {

    private Product currentProduct;
    @FXML
    private ImageView ProductImage;

    @FXML
    private Label ItemName;

    @FXML
    private Label ItemDiscription;

    private Label ItemPrice;
    private Label ItemQuantity;

    @FXML
    private Button AddToCartBT;

    @FXML
    private Button BuyNowBT;

    private Connection connection;

    private Database DatabaseConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection = new Database();
    }

    private void SetProductInfo() throws SQLException, ClassNotFoundException, IOException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/javafinalproject", "root", "Lukeisapuppy87?");
        connection = DatabaseConnection.InitializeDatabase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPage.fxml"));
        Parent root = loader.load();
        ItemPage itemPage = loader.getController();
    }

    @FXML
    void recieveData(Product product) {
        currentProduct = product;
        ItemName.setText(product.getName());
        //ItemDiscription.setText(product.getDescription());

        System.out.println("*****************************");

        String ip = product.getImagePath();
        ItemDiscription.setText(ip);

        System.out.println(ip);
        //Image y = new Image("Baseball.jpeg");
        //ImageView x = new ImageView("Baseball.jpeg");
        ProductImage = new ImageView(getClass().getResource(ip).toExternalForm());
        ProductImage.setFitHeight(100);
        ProductImage.setFitWidth(100);
        //x.setImage(y);
        //ProductImage.setImage(new Image(ip));
        System.out.println("%%%%%%%%%%%%%%%%%");

        //imageView.setFitHeight(100);
        //imageView.setFitWidth(100);

        //ProductImage.setImage(imageView.getImage());
        //ProductImage = product.getImagePath();
        //ItemPrice.setText(Double.valueOf(product.getPrice()).toString());
        //ItemQuantity.setText(Integer.valueOf(product.getQuantity()).toString());

        //Image image = new Image(product.getImagePath());
        //ProductImage.setImage();
    }

    public void sendData() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        Parent root = loader.load();
        CartDef controller = loader.getController();
        controller.recieveData(currentProduct);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addToCart(ActionEvent event) throws IOException {
        sendData();
    }

    public void goToCart(ActionEvent event) throws IOException {
        sendData();
    }
}
