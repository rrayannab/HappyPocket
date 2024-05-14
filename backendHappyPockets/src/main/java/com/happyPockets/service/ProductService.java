package com.happyPockets.service;

import org.apache.poi.ss.usermodel.*;
import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.ShopPrice;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> productList;

    public ProductService(){
        productList = new ArrayList<>();
        /*
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
        */
        String rutaArchivo = "productos.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(rutaArchivo))) {
            // Abrir el libro de Excel
            Workbook libro = WorkbookFactory.create(fis);

            Sheet hoja = libro.getSheetAt(0);

            int i = 0;

            for (Row fila : hoja) {
                int id = ++i;
                String name = fila.getCell(0).getStringCellValue();
                String brand = fila.getCell(1).getStringCellValue();
                String cat = fila.getCell(2).getStringCellValue();
                ShopPrice shopPrices = new ShopPrice(fila.getCell(3).getNumericCellValue(), fila.getCell(4).getNumericCellValue(), fila.getCell(5).getNumericCellValue());
                String imageLink = fila.getCell(6).getStringCellValue();

                Product product = new Product(id, name, brand, cat, shopPrices, imageLink);

                productList.add(product);
            }
            libro.close();
        } catch (Exception ex) { }
        
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
