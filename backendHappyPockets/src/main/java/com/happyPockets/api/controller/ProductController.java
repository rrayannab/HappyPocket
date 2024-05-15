package com.happyPockets.api.controller;

import com.happyPockets.api.model.Product;
import com.happyPockets.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/product")
    public Product getProduct(@RequestParam Integer id){
        Optional<Product> product = productService.getProductId(id);
        if (product.isPresent())
            return product.get();
        return null;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam Optional<String> order,
            @RequestParam Optional<Double> priceFrom,
            @RequestParam Optional<Double> priceTo){
        String orderValue = order.orElse("");
        List<Product> productList =  productService.getProductList();

        if (order != null && !orderValue.isEmpty())
            productList =  ProductService.getProductListOrder(productList, orderValue);

        productList = ProductService.getProductPriceRange(productList, priceFrom, priceTo);

        if (productList!=null)
            return ResponseEntity.ok(productList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);


    }
}
