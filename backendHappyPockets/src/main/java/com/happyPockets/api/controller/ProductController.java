package com.happyPockets.api.controller;

import com.happyPockets.api.model.Product;
import com.happyPockets.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
    * Endpoint para obtener un producto por su id.
    * @param id int
     */
    @GetMapping("/product")
    public Product getProduct(@RequestParam Integer id){
        Optional<Product> product = productService.getProductId(id);
        if (product.isPresent())
            return product.get();
        return null;
    }

    /**
    * Endpoint para obtener la lista de productos
    * Se pueden filtrar por: precio ascendete y descendente, rango de precios, categoría y nombre del producto.
    * order: {"asc", "desc", "priceAsc", "priceDesc"
    * @param priceFrom double
    * @param priceTo double
    * @param category string
    * @param producto string
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam Optional<String> order,
            @RequestParam Optional<Double> priceFrom,
            @RequestParam Optional<Double> priceTo,
            @RequestParam Optional<String> category,
            @RequestParam Optional<String> producto
            ){
        String orderValue = order.orElse("");
        List<Product> productList =  productService.getProductList();

        if (order != null && !orderValue.isEmpty())
            productList =  ProductService.getProductListOrder(productList, orderValue);

        productList = ProductService.getProductSearch(productList, producto);
        productList = ProductService.getProductPriceRange(productList, priceFrom, priceTo);

        productList = ProductService.getProductCategory(productList, category);

        if (productList!=null)
            return ResponseEntity.ok(productList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
    * Endpoint para obtener la lista de categorías
    */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories(){
        return ResponseEntity.ok(productService.getCategories());
    }

    /**
    * Endpoint para obtener la lista de marcas
     */
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrands(){
        return ResponseEntity.ok(productService.getBrands());
    }


}
