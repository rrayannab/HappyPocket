package com.happyPockets.api.model;

import java.util.Comparator;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private double price;
    private String imageLink;
    private ShopPrice shopPrices;

    public Product(int id, String name, String link, ShopPrice shopPrices) {
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

    public ShopPrice getShopPrices() {
        return shopPrices;
    }

    public void setShopPrices(ShopPrice shopPrices) {
        this.shopPrices = shopPrices;
    }

    public double getBestPrice(){
        return shopPrices.getBestPrice();
    }

    public String getCheapesStore(){
//        return shopPrices
        return "";
    }

    // Método para comparar productos por nombre
    @Override
    public int compareTo(Product product2) {
        return this.name.compareTo(product2.getName());
    }

    // Método para comparar productos por precio
    public static Comparator<Product> comparadorPorPrecio = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getBestPrice(), p2.getBestPrice());
        }
    };

}
