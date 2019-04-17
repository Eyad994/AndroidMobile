package com.jamalonexpress.fragmentwithnavigation.Models;

public class Book {

    private String sku;
    private String title;
    private String image;
    private String price;
    private int qty = 1;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private String author;

    public Book() {
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSku() {
        return sku;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public Book(String sku, String title, String image, String price, int qty, String author) {
        this.sku = sku;
        this.title = title;
        this.image = image;
        this.price = price;
        this.qty = this.qty;
        this.author = author;
    }
}
