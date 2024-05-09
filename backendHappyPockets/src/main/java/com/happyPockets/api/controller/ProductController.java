package com.happyPockets.api.controller;

import com.happyPockets.api.model.Product;
import com.happyPockets.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Product> getProducts(){
        return productService.getProductList();
    }
}
