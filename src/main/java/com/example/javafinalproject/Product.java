package com.example.javafinalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Product {
    private String name;
    private String description;
    private ImageView image;
    String imagePath;
    private double price;
    private int quantity;

    public Product(String name, double price, ImageView image, String description, int quantity, String imagePath) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.imagePath = imagePath;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
