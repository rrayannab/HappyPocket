package com.happyPockets.service;

import org.apache.poi.ss.usermodel.*;
import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.ShopPrice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
            productList = getProductListOrder(productList, "asc");

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

    public static List<Product> getProductListOrder(List<Product> products, String order) {
        if (products == null || order == null)
            return null;
        if (order.equalsIgnoreCase("asc")) {
            products.sort(Comparator.comparing(Product::getName));
            return products;
        } else if (order.equalsIgnoreCase("desc")) {
            products.sort(Comparator.comparing(Product::getName).reversed());
            return products;
        } else if (order.equalsIgnoreCase("priceAsc")){
            products.sort(Product.comparadorPorPrecio);
            return products;
        }else if (order.equalsIgnoreCase("priceDesc")){
            products.sort(Product.comparadorPorPrecio);
            Collections.reverse(products);
            return products;
        }else return null;
    }

    public static List<Product> getProductPriceRange(
            List<Product> productListIn,
            @RequestParam Optional<Double> priceFrom,
            @RequestParam Optional<Double> priceTo){
        if (productListIn == null || (priceFrom.isPresent() && priceFrom.get() < 0) || (priceTo.isPresent() && priceTo.get() < 0))
            return null;
        if (priceFrom.isPresent() && priceTo.isPresent() && priceFrom.get() > priceTo.get())
            return null;
        if (priceFrom.isPresent()){
            productListIn = productListIn.stream().filter(product -> product.getShopPrices().getBestPrice() >= priceFrom.get()).toList();
        }
        if (priceTo.isPresent()){
            productListIn = productListIn.stream().filter(product -> product.getShopPrices().getBestPrice() <= priceTo.get()).toList();
        }

        return productListIn;
    }

    public static List<Product> getProductCategory(List<Product> productListIn, @RequestParam Optional<String> category){
        if (productListIn == null || category == null)
            return productListIn;
        if (category.isPresent()){
            productListIn = productListIn.stream().filter(product -> product.getCat().equalsIgnoreCase(category.get())).toList();
        }
        return productListIn;
    }

    public static List<Product> getProductSearch(List<Product> productListIn, @RequestParam Optional<String> product){
        if (productListIn == null)
            return productListIn;
        if (product != null && product.isPresent()){
            productListIn = productListIn.stream().filter(product1 -> product1.getName().toLowerCase().contains(product.get().toLowerCase())).toList();
        }
        return productListIn;
    }

    public List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        for (Product product : productList) {
            if (!categories.contains(product.getCat())){
                categories.add(product.getCat());
            }
        }
        return categories;
    }

    public List<String> getBrands(){
        List<String> brands = new ArrayList<>();
        for (Product product : productList) {
            if (!brands.contains(product.getBrand())){
                brands.add(product.getBrand());
            }
        }
        return brands;
    }
}
