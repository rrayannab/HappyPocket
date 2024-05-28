package com.happyPockets;

import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.ShopPrice;
import com.happyPockets.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.happyPockets.api.controller.ProductController;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    @BeforeEach
    void setUp() {
        ShopPrice shopPrices1 = new ShopPrice(10.0, 15.0, 11.5);
        ShopPrice shopPrices2 = new ShopPrice(8.0, 12.0, 9.99);
        ShopPrice shopPrices3 = new ShopPrice(5.0, 20.0, 7.5);
        ShopPrice shopPrices4 = new ShopPrice(7.0, 18.0, 8.5);

        product1 = new Product(1, "Product A", "Brand A", "Category A", shopPrices1, "link1");
        product2 = new Product(2, "Product B", "Brand B", "Category B", shopPrices2, "link2");
        product3 = new Product(3, "Product C", "Brand C", "Category C", shopPrices3, "link3");
        product4 = new Product(4, "Product D", "Brand D", "Category D", shopPrices4, "link4");
    }
    @Test
    void testGetProduct() throws Exception {
        when(productService.getProductId(1)).thenReturn(Optional.of(product1));

        mockMvc.perform(get("/product")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product A"))
                .andExpect(jsonPath("$.shopPrices.bestPrice").value(10.0));
    }

    @Test
    void testGetProductsDesc() throws Exception {
        List<Product> products = Arrays.asList(product1, product2, product3, product4);
        when(productService.getProductList()).thenReturn(products);

        mockMvc.perform(get("/products")
                        .param("order", "desc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id").value(4))
                .andExpect(jsonPath("$[0].name").value("Product D"))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[1].name").value("Product C"))
                .andExpect(jsonPath("$[2].id").value(2))
                .andExpect(jsonPath("$[2].name").value("Product B"))
                .andExpect(jsonPath("$[3].id").value(1))
                .andExpect(jsonPath("$[3].name").value("Product A"));
    }

    @Test
    void testGetProductsPriceAsc() throws Exception {
        List<Product> products = Arrays.asList(product1, product2, product3, product4);
        when(productService.getProductList()).thenReturn(products);

        mockMvc.perform(get("/products")
                        .param("order", "priceAsc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].name").value("Product C"))
                .andExpect(jsonPath("$[1].id").value(4))
                .andExpect(jsonPath("$[1].name").value("Product D"))
                .andExpect(jsonPath("$[2].id").value(2))
                .andExpect(jsonPath("$[2].name").value("Product B"))
                .andExpect(jsonPath("$[3].id").value(1))
                .andExpect(jsonPath("$[3].name").value("Product A"));
    }

    @Test
    void testGetProductsPriceRange() throws Exception {
        List<Product> products = Arrays.asList(product1, product2, product3, product4);
        when(productService.getProductList()).thenReturn(products);

        mockMvc.perform(get("/products")
                        .param("priceFrom", "5.0")
                        .param("priceTo", "7.0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].name").value("Product C"))
                .andExpect(jsonPath("$[1].id").value(4))
                .andExpect(jsonPath("$[1].name").value("Product D"));
    }

    @Test
    void testGetCategories() throws Exception {
        List<String> categories = Arrays.asList("Category A", "Category B", "Category C", "Category D");
        when(productService.getCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]").value("Category A"))
                .andExpect(jsonPath("$[1]").value("Category B"))
                .andExpect(jsonPath("$[2]").value("Category C"))
                .andExpect(jsonPath("$[3]").value("Category D"));
    }

    @Test
    void testGetBrands() throws Exception {
        List<String> brands = Arrays.asList("Brand A", "Brand B", "Brand C", "Brand D");
        when(productService.getBrands()).thenReturn(brands);

        mockMvc.perform(get("/brands")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]").value("Brand A"))
                .andExpect(jsonPath("$[1]").value("Brand B"))
                .andExpect(jsonPath("$[2]").value("Brand C"))
                .andExpect(jsonPath("$[3]").value("Brand D"));
    }
}
