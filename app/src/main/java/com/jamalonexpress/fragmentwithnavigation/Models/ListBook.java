package com.jamalonexpress.fragmentwithnavigation.Models;

public class ListBook {

    private String title;
    private String publisher;
    private int price;

    public ListBook() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ListBook(String title, String publisher, int price) {
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }
}
