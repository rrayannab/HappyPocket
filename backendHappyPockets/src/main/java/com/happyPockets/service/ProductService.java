package com.happyPockets.service;

import org.apache.poi.ss.usermodel.*;
import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.ShopPrice;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.*;

@Service
public class ProductService {

    private List<Product> productList;
    private final String rutaArchivo = ".\\src\\main\\java\\com\\happyPockets\\service\\productos.xlsx";

    public ProductService(){
        productList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(rutaArchivo)))
        {
            // Abrir el libro de Excel
            Workbook libro = WorkbookFactory.create(fis);

            Sheet hoja = libro.getSheetAt(0);

            int i = 0;

            for (Row fila : hoja) {
                if (fila.getRowNum() < 2) continue;
                else if (fila.getCell(0) == null) break;

                try {
                    String name = fila.getCell(0).getStringCellValue();
                    String brand = fila.getCell(1).getStringCellValue();
                    String cat = fila.getCell(2).getStringCellValue();
                    ShopPrice shopPrices = new ShopPrice(fila.getCell(3).getNumericCellValue(), fila.getCell(4).getNumericCellValue(), fila.getCell(5).getNumericCellValue());
                    String imageLink = fila.getCell(6).getStringCellValue();
                    int id = ++i;

                    Product product = new Product(id, name, brand, cat, shopPrices, imageLink);

                    productList.add(product);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            libro.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

    public List<Product> getProductList(String order) {
        List<Product> products = getProductList();
        if (order.equalsIgnoreCase("ascendente")) {
            products.sort(Comparator.comparing(Product::getName));
            return products;
        } else if (order.equalsIgnoreCase("descendente")) {
            products.sort(Comparator.comparing(Product::getName).reversed());
            return products;
        } else if (order.equalsIgnoreCase("precio")){
            products.sort(Product.comparadorPorPrecio);
            return products;
        }else return null;
    }
}
