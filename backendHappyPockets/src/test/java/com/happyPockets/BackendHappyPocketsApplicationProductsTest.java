package com.happyPockets;

import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.ShopPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackendHappyPocketsApplicationProductsTest {
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void setUp() {
        ShopPrice shopPrices1 = new ShopPrice(10.0, 15.0, 11.5);
        ShopPrice shopPrices2 = new ShopPrice(8.0, 12.0, 9.99);
        ShopPrice shopPrices3 = new ShopPrice(9.0, 14.0, 10);

        product1 = new Product(1, "Product A", "Brand A", "Category A", shopPrices1, "link1");
        product2 = new Product(2, "Product B", "Brand B", "Category B", shopPrices2, "link2");
        product3 = new Product(3, "Product C", "Brand C", "Category C", shopPrices3, "link3");
    }

    @Test
    public void CompareTo() {
        assertTrue(product1.compareTo(product2) < 0); // Se espera que "Product A" sea menor que "Product B"
        assertTrue(product2.compareTo(product3) < 0); // Se espera que "Product C" sea mayor que "Product B"
        assertEquals(0, product1.compareTo(product1)); // Se espera que "Product A" sea igual a "Product A"
    }

    @Test
    public void ComparadorPorPrecio() {
        assertTrue(Product.comparadorPorPrecio.compare(product1, product2) > 0); // Se espera que "Product A" sea mayor que "Product B" en precio
        assertTrue(Product.comparadorPorPrecio.compare(product2, product3) < 0); // Se espera que "Product B" sea menor que "Product C" en precio
        assertEquals(0, Product.comparadorPorPrecio.compare(product1, product1)); // Se espera que "Product A" sea igual a "Product A" en precio
    }

    @Test
    public void testSettersAndGetters() {
        product1.setBrand("New Brand");
        assertEquals("New Brand", product1.getBrand());

        product2.setCat("New Category");
        assertEquals("New Category", product2.getCat());

        product3.setImageLink("New Link");
        assertEquals("New Link", product3.getImageLink());

        product1.setId(100);
        assertEquals(100, product1.getId());

        product2.setName("New Name");
        assertEquals("New Name", product2.getName());

        // Prueba para el setter y getter de shopPrices
        ShopPrice newShopPrices = new ShopPrice(20.0, 25.0, 21.4);
        product3.setShopPrices(newShopPrices);
        assertEquals(newShopPrices, product3.getShopPrices());
    }
}
