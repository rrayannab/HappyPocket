package com.happyPockets.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class ShopPrice {
    final String CARREFOUR = "Carrefour";
    final String DIA = "Dia";
    final String CORTE_INGLES = "Corte Ingles";
    final String EQUAL = "Equal";

    private String[] stores = new String[] {CARREFOUR, DIA, CORTE_INGLES};
    private double[] prices = new double[3];


    public ShopPrice(double priceCarrefour, double priceDia, double priceCorteIngles) {;
        this.prices = new double[] {priceCarrefour, priceDia, priceCorteIngles};
    }

    @Override
    public String toString() {
        return "ShopPrice{" +
                "Best Price=" + getBestPrice() + '\'' +
                "Best Store=" + getBestStore() + '\'' +
                "Prices{" + CARREFOUR + "='" + prices[0] + '\'' +
                DIA + "='" + prices[1] + '\'' +
                CORTE_INGLES + "='" + prices[2] + '\'' +
                "}}";
    }

    @JsonIgnore
    public double getPriceCarrefour() {
        return this.prices[0];
    }

    public void setPriceCarrefour(double priceCarrefour) {
        this.prices[0] = priceCarrefour;
    }

    @JsonIgnore
    public double getPriceDia() {
        return this.prices[1];
    }

    public void setPriceDia(double priceDia) {
        this.prices[1] = priceDia;
    }

    @JsonIgnore
    public double getPriceCorteIngles() {
        return this.prices[2];
    }

    public void setPriceCorteIngles(double priceCorteIngles) {
        this.prices[2] = priceCorteIngles;
    }

    // Devuelve el índice de la tienda con el precio más barato
    private int getCheapestIndex(){
        double min = this.prices[0];
        int index = 0;
        for (int i = 1; i < this.prices.length; i++) {
            if (this.prices[i] < min) {
                min = this.prices[i];
                index = i;
            }
        }
        return index;
    }

    // Devuelve una lista con los precios de las tiendas
    public double[] getPrices(){
        return this.prices;
    }

    // Devuelve una lista con los nombres de las tiendas
    public String[] getStores(){
        return this.stores;
    }

    // Devuelve el precio más barato
    public double getBestPrice() {
        return this.prices[getCheapestIndex()];
    }

    // Devuelve la tienda con el precio más barato
    public String getBestStore() {
        return this.stores[getCheapestIndex()];
    }
}
