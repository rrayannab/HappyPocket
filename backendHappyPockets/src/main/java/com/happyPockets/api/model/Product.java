package com.happyPockets.api.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String imageLink;
    private ShopPrice[] shopPrices;

    public Product(int id, String name, String link, ShopPrice[] shopPrices) {
        this.imageLink = link;
        this.price = price;
        this.name = name;
        this.id = id;
        this.shopPrices = shopPrices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String link) {
        this.imageLink = link;
    }

    public ShopPrice[] getShopPrices() {
        return shopPrices;
    }

    public void setShopPrices(ShopPrice[] shopPrices) {
        this.shopPrices = shopPrices;
    }
}
