package com.happyPockets.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShopPrice {
    final String CARREFOUR = "Carrefour";
    final String DIA = "Dia";
    final String CORTE_INGLES = "Corte Ingles";
    final String EQUAL = "Equal";

    private String[] stores = new String[] {CARREFOUR, DIA, CORTE_INGLES};
    private double[] prices = new double[3];
    private String cheapestStore;
//    private String link;


    public ShopPrice(double priceCarrefour, double priceDia, double priceCorteIngles) {;
        this.prices = new double[] {priceCarrefour, priceDia, priceCorteIngles};
        this.cheapestStore = getCheapestStore();
    }

    @JsonIgnore
    public double getPriceCarrefour() {
        return this.prices[0];
    }

    public void setPriceCarrefour(double priceCarrefour) {
        this.prices[0] = priceCarrefour;
        this.cheapestStore = getCheapestStore();
    }

    @JsonIgnore
    public double getPriceDia() {
        return this.prices[1];
    }

    public void setPriceDia(double priceDia) {
        this.prices[1] = priceDia;
        this.cheapestStore = getCheapestStore();
    }

    @JsonIgnore
    public double getPriceCorteIngles() {
        return this.prices[2];
    }

    public void setPriceCorteIngles(double priceCorteIngles) {
        this.prices[2] = priceCorteIngles;
        this.cheapestStore = getCheapestStore();
    }

    public String getCheapestStore(){
//        if (this.prices[0] == this.prices[1] && this.prices[1] == this.prices[2])
//            return EQUAL;

        double min = this.prices[0];
        int index = 0;
        for (int i = 1; i < this.prices.length; i++) {
            if (this.prices[i] < min) {
                min = this.prices[i];
                index = i;
            }
        }
        return stores[index];
    }

    public double[] getPrices(){
        return this.prices;
    }

    public String[] getStores(){
        return this.stores;
    }

    @JsonIgnore
    public double getBestPrice() {
        if (this.cheapestStore.equals(CARREFOUR))
            return this.prices[0];
        else if (this.cheapestStore.equals(DIA))
            return this.prices[1];
        else
            return this.prices[2];
    }
}
