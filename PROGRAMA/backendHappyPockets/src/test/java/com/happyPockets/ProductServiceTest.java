package com.happyPockets;

import com.happyPockets.api.model.Product;
import com.happyPockets.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase dedicada a las pruebas unitarias de cada uno de los metodos
 * de la calse ProductService
 * 		7 pruebas unitarias
 */
@SpringBootTest
public class ProductServiceTest {
	ProductService productService = null;

	@BeforeEach
	void getProdectService() {
		productService = new ProductService();
	}

	/**
	 * Preuba para los metodos:
	 * 		getProductId
	 */
	@Test
	void ProductInDataBaseTest() {
		for (Product pr : productService.getProductList()) {
			assertTrue(productService.getProductId(pr.getId()).isPresent());
			Product aux = productService.getProductId(pr.getId()).get();
			assertEquals(pr.getName(),aux.getName());
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		getProductId
	 */
	@Test
	void ProductNotInDataBaseTest() {
		int maxId = -1;
		for (Product pr : productService.getProductList()) {
			if (maxId < pr.getId()) {
				maxId = pr.getId();
			}
		}
		assertFalse(productService.getProductId(maxId+1).isPresent());
	}

	/**
	 * Preuba para los metodos:
	 * 		getProductPriceRange
	 */
	@Test
	void ProductgetProductPriceRange() {
		Optional<Double> priceFrom = null;
		Optional<Double> priceTo = null;
		List<Product> productList = null;

		productList = productService.getProductPriceRange(null,priceFrom, priceTo);
		assertNull(productList);

		priceFrom = Optional.empty();
		priceTo = Optional.empty();
		productList = productService.getProductPriceRange(null,priceFrom, priceTo);
		assertNull(productList);

		priceFrom = Optional.of(1.0);
		priceTo = Optional.empty();
		productList = productService.getProductPriceRange(productService.getProductList(),priceFrom, priceTo);
		assertNotNull(productList);

		priceFrom = Optional.empty();
		priceTo = Optional.of(10.0);
		productList = productService.getProductPriceRange(productService.getProductList(),priceFrom, priceTo);
		assertNotNull(productList);

		priceFrom = Optional.of(-1.0);
		priceTo = Optional.empty();
		productList = productService.getProductPriceRange(productService.getProductList(),priceFrom, priceTo);
		assertNull(productList);

		priceFrom = Optional.empty();
		priceTo = Optional.of(-1.0);
		productList = productService.getProductPriceRange(productService.getProductList(),priceFrom, priceTo);
		assertNull(productList);

		priceFrom = Optional.of(2.0);
		priceTo = Optional.of(1.0);
		productList = productService.getProductPriceRange(productService.getProductList(),priceFrom, priceTo);
		assertNull(productList);

		priceFrom = Optional.of(5.0);
		priceTo = Optional.of(10.0);
		productList = productService.getProductPriceRange(productService.getProductList(),priceFrom, priceTo);
		for (Product pr : productList) {
			assertTrue(pr.getShopPrices().getBestPrice() >= priceFrom.get() &&
					pr.getShopPrices().getBestPrice() <= priceTo.get());
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		getProductCategory
	 */
	@Test
	void ProductCategory() {
		assertNull(productService.getProductCategory(null, Optional.empty()));
		assertNull(productService.getProductCategory(null, null));
		assertNotNull(productService.getProductCategory(productService.getProductList(), null));

		Map<String, Set<String>> categorias = new HashMap<>();
		List<Product> productList = productService.getProductList();

		for (Product pr : productList) {
			Set<String> productos = categorias.getOrDefault(pr.getCat(), new HashSet<>());
			productos.add(pr.getName());
		}

		for (String cat : categorias.keySet()) {
			for (Product pr : productService.getProductCategory(productList, Optional.of(cat))) {
				assertTrue(categorias.get(cat).contains(pr.getName()));
			}
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		getProductSearch
	 */
	@Test
	void ProductSearch() {
		assertNull(productService.getProductSearch(null, null));
		assertNotNull(productService.getProductSearch(productService.getProductList(), null));
		assertNotNull(productService.getProductSearch(productService.getProductList(), Optional.empty()));

		for (Product pr : productService.getProductList()) {
			assertTrue(productService.getProductSearch(productService.getProductList(), Optional.of(pr.getName())).contains(pr));
		}

		assertNotNull(productService.getProductSearch(productService.getProductList(), Optional.of("")));
	}

	/**
	 * Preuba para los metodos:
	 * 		getProductListOrder
	 */
	@Test
	void ProductListOrder() {
		assertNull(productService.getProductListOrder(null, null));
		assertNull(productService.getProductListOrder(null, ""));
		assertNull(productService.getProductListOrder(productService.getProductList(), null));
		assertNull(productService.getProductListOrder(productService.getProductList(), ""));
		assertNull(productService.getProductListOrder(productService.getProductList(), "prueba"));

		for (String ord : List.of("asc", "desc", "priceAsc", "priceDesc")) {
			assertEquals(productService.getProductListOrder(productService.getProductList(), ord).size(), productService.getProductList().size());
		}

	}

	/**
	 * Preuba para los metodos:
	 * 		getCategories
	 */
	@Test
	void ProductCategories() {
		Set<String> categorias = new HashSet<>();
		for (Product pr : productService.getProductList()) {
				categorias.add(pr.getCat());
		}
		for (String cat : productService.getCategories()) {
			assertTrue(categorias.contains(cat));
		}
	}
}
