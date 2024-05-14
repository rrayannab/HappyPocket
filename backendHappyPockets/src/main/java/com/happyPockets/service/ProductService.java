package com.happyPockets.service;

import org.apache.poi.ss.usermodel.*;
import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.ShopPrice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> productList;

    public ProductService(){
        productList = new ArrayList<>();

        Product product1 = new Product(1, "Galletas", "Marca", "Categoria",
            new ShopPrice(1.5, 1.6, 1.7),
            "https://www.carrefour.es/galletas"
        );
        Product product2 = new Product(2, "Leche", "Marca", "Categoria",
            new ShopPrice(1.5, 1.6, 1.7),
            "https://www.carrefour.es/leche"
        );
        Product product3 = new Product(3, "Pan", "Marca", "Categoria",
            new ShopPrice(1.5, 1.6, 1.7),
            "https://www.carrefour.es/pan"
        );
        Product product4 = new Product(4, "Cerveza", "Marca", "Categoria",
            new ShopPrice(1.5, 1.6, 1.7),
            "https://www.carrefour.es/cerveza"
        );
        Product product5 = new Product(5, "Vino", "Marca", "Categoria",
            new ShopPrice(1.5, 1.6, 1.7),
            "https://www.carrefour.es/vino"
        );
        Product product6 = new Product(6, "Queso", "Marca", "Categoria",
            new ShopPrice(1.5, 1.6, 1.7),
            "https://www.carrefour.es/queso"
        );
        Workbook libro = new Workbook("productos.xlsx");

        Sheet hoja = libro.getSheetAt(0);

        int i = 0;

        for (Row fila : hoja) {
            int id = i;
            String name = fila.getCell(0).getStringCellValue();

            String imageLink = fila.getCell(1).getStringCellValue();
            ShopPrice[] shopPrices;

        }

        /*
        Product product1 = new Product(1, "Galletas", "https://www.carrefour.es/galletas", new ShopPrice[]{
                new ShopPrice(1.5, 1.6, 1.7)
        });
        Product product2 = new Product(2, "Leche", "https://www.carrefour.es/leche", new ShopPrice[]{
                new ShopPrice(1.5, 1.6, 1.7)
        });
        Product product3 = new Product(3, "Pan", "https://www.carrefour.es/pan", new ShopPrice[]{
                new ShopPrice(1.5, 1.6, 1.7)
        });
        Product product4 = new Product(4, "Cerveza", "https://www.carrefour.es/cerveza", new ShopPrice[]{
                new ShopPrice(1.5, 1.6, 1.7)
        });
        Product product5 = new Product(5, "Vino", "https://www.carrefour.es/vino", new ShopPrice[]{
                new ShopPrice(1.5, 1.6, 1.7)
        });
        Product product6 = new Product(6, "Queso", "https://www.carrefour.es/queso", new ShopPrice[]{
                new ShopPrice(1.5, 1.6, 1.7)
        });
         */

        productList.addAll(Arrays.asList(product1, product2, product3, product4, product5, product6));
    }

    public Optional<Product> getProductId(Integer id) {
        Optional<Product> optinal = Optional.empty();

        for (Product product : productList) {
            if (product.getId() == id) {
                optinal = Optional.of(product);
                return optinal;
            }
        }
        return optinal;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
