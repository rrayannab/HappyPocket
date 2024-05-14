package com.happyPockets.service;

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
