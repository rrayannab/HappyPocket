package com.happyPockets.api.model;

public class ShopPrice {
    final String CARREFOUR = "Carrefour";
    final String DIA = "Dia";
    final String CORTE_INGLES = "Corte Ingles";
    final String EQUAL = "Equal";

    private double priceCarrefour;
    private double priceDia;
    private double priceCorteIngles;
    private String cheapestStore;
//    private String link;


    public ShopPrice(double priceCarrefour, double priceDia, double priceCorteIngles) {
        this.priceCarrefour = priceCarrefour;
        this.priceDia = priceDia;
        this.priceCorteIngles = priceCorteIngles;
        this.cheapestStore = getCheapestStore();
    }

    public double getPriceCarrefour() {
        return priceCarrefour;
    }

    public void setPriceCarrefour(double priceCarrefour) {
        this.priceCarrefour = priceCarrefour;
        this.cheapestStore = getCheapestStore();
    }

    public double getPriceDia() {
        return priceDia;
    }

    public void setPriceDia(double priceDia) {
        this.priceDia = priceDia;
        this.cheapestStore = getCheapestStore();
    }

    public double getPriceCorteIngles() {
        return priceCorteIngles;
    }

    public void setPriceCorteIngles(double priceCorteIngles) {
        this.priceCorteIngles = priceCorteIngles;
        this.cheapestStore = getCheapestStore();
    }

    public String getCheapestStore(){
        if(priceCarrefour < priceDia && priceCarrefour < priceCorteIngles){
            return CARREFOUR;
        }else if(priceDia < priceCarrefour && priceDia < priceCorteIngles){
            return DIA;
        }else if(priceCorteIngles < priceCarrefour && priceCorteIngles < priceDia){
            return CORTE_INGLES;
        }
        return EQUAL;

    }
}
