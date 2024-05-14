package com.happyPockets.api.model;

import java.util.Comparator;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private String imageLink;
    private ShopPrice shopPrices;
    private String brand;
    private String cat;

    public Product(int id, String name, String brand, String cat, ShopPrice shopPrices, String link) {
        this.imageLink = link;
        this.name = name;
        this.id = id;
        this.shopPrices = shopPrices;
        this.brand = brand;
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", cat='" + cat + '\'' +
                ", shopPrices=" + shopPrices +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
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

    public String getBestStore(){
        return shopPrices.getBestStore();
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
