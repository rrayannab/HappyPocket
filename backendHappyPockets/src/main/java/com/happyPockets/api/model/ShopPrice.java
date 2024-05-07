package com.happyPockets.api.model;

public class ShopPrice {
    private double priceCarrefour;
    private double priceDia;
    private double priceCorteIngles;
//    private String link;


    public ShopPrice(double priceCarrefour, double priceDia, double priceCorteIngles) {
        this.priceCarrefour = priceCarrefour;
        this.priceDia = priceDia;
        this.priceCorteIngles = priceCorteIngles;
    }

    public double getPriceCarrefour() {
        return priceCarrefour;
    }

    public void setPriceCarrefour(double priceCarrefour) {
        this.priceCarrefour = priceCarrefour;
    }

    public double getPriceDia() {
        return priceDia;
    }

    public void setPriceDia(double priceDia) {
        this.priceDia = priceDia;
    }

    public double getPriceCorteIngles() {
        return priceCorteIngles;
    }

    public void setPriceCorteIngles(double priceCorteIngles) {
        this.priceCorteIngles = priceCorteIngles;
    }
}
