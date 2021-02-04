package com.example.qualitycam;

public class SingleCard {
    private String title;
    private int image;
    private int price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SingleCard(String title, int image, int price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }
}
