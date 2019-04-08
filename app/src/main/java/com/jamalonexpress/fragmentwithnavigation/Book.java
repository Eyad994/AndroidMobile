package com.jamalonexpress.fragmentwithnavigation;

public class Book {

    private String sku;
    private String title;
    private String image;
    private String price;
    private String qty;
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

    public void setQty(String qty) {
        this.qty = qty;
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

    public String getQty() {
        return qty;
    }

    public String getAuthor() {
        return author;
    }

    public Book(String sku, String title, String image, String price, String qty, String author) {
        this.sku = sku;
        this.title = title;
        this.image = image;
        this.price = price;
        this.qty = qty;
        this.author = author;
    }
}
