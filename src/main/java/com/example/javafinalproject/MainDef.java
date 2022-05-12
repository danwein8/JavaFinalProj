package com.example.javafinalproject;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class MainDef implements Initializable {
    @FXML
    private TableView<Product> productTable;
    //@FXML
    private TableColumn<Product, String> Name;
    //@FXML
    private TableColumn<Product, Double> Price;
    //@FXML
    private TableColumn<Product, Void> ViewProduct;
    //@FXML
    private TableColumn<Product, ImageView> Image;


    private Connection connection;

    private Database databaseConnection;

    private ObservableList<Product> products;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseConnection = new Database();
        populateTable();
    }

    private void populateTable() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javafinalproject", "root", "Lukeisapuppy87?");
            connection = databaseConnection.InitializeDatabase();
            products = FXCollections.observableArrayList();
            String query = "SELECT * FROM products";
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                String description = rs.getString("Discription");
                int quantity = rs.getInt("Quantity");
                String imagepath = rs.getString("Image");
                ImageView imageView = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("Image"))));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                Product product = new Product(name, price, imageView, description, quantity, imagepath);
                products.add(product);
            }
            Name = new TableColumn<Product, String>("name");
            Name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            Price = new TableColumn<Product, Double>("price");
            Price.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            Image = new TableColumn<Product, ImageView>("image");
            Image.setCellValueFactory(new PropertyValueFactory<>("image"));
            productTable.getColumns().addAll(Name, Price, Image);
            addButtonToTable();
            Platform.runLater(() -> customResize(productTable));
            productTable.setItems(products);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void addButtonToTable() {
        TableColumn<Product, Void> colBtn = new TableColumn("Add");
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {
                    private final Button btn = new Button("View Item");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Product data = getTableView().getItems().get(getIndex());
                            System.out.println("Clicked on: " + data.getName());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPage.fxml"));
                                Parent root = (Parent) loader.load();
                                ItemPageDef controller = loader.getController();
                                controller.recieveData(getTableView().getItems().get(getIndex()));
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        productTable.getColumns().add(colBtn);
    }

    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
    @FXML
    public void sendData(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemPage.fxml"));
        Parent root = (Parent) loader.load();
        ItemPageDef controller = loader.getController();
        controller.recieveData(productTable.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


    }
}
