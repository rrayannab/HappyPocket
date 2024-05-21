package com.happyPockets;

import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.User;
import com.happyPockets.service.ProductService;
import com.happyPockets.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BackendHappyPocketsApplicationTestsProductService {
	ProductService productService = null;

	@BeforeEach
	void getProdectService() {
		productService = new ProductService();
	}

	@Test
	void ProductInDataBaseTest() {
		for (Product pr : productService.getProductList()) {
			assertTrue(productService.getProductId(pr.getId()).isPresent());
			Product aux = productService.getProductId(pr.getId()).get();
			assertEquals(pr.getName(),aux.getName());
		}
	}

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

	@Test
	void ProductCategory() {
		assertNull(productService.getProductCategory(null, Optional.empty()));
		assertNull(productService.getProductCategory(null, null));
		assertNull(productService.getProductCategory(productService.getProductList(), null));

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

	/*
	@Test
	void UserLogInFalseTest() {
		List<User> userList = userService.getUserList();
		if (userList.isEmpty()) {
			assertFalse(userService.logIn("test", "test"));
		} else {
			for (User us : userService.getUserList()) {
				assertFalse(userService.logIn(us.getUsername(), us.getPassword()+"test"));
				assertFalse(userService.logIn(us.getUsername()+"test", us.getPassword()));
			}
		}
	}

	@Test
	void AddUserFalse() {
		for (User us : userService.getUserList()) {
			assertFalse(userService.addUser(us.getUsername(), us.getPassword(), us.getEmail(), us.getName(), us.getSurname1(), us.getSurname2(), us.getPhone()));
		}
	}

	// Para que la prueba sea mas concisa deberia de poder borrarse el usuario del test para que no se quede en la base de datos
	// Si se queda en la base de datos para la siquiente iteracion fallará
	@Test
	void AddUserTrue() {
		int num = new Random().nextInt();
		if (num < 0)
			num = num*(-1);
		assertTrue(userService.addUser("test"+num, "test1234", "test"+num+"@gmail.com", "test", "test", "test", 123456));
		Set<String> users = new HashSet<>();
		for (User us : userService.getUserList()) {
			users.add(us.getUsername());
		}
		assertTrue(users.contains("test"+num));
	}
	 */
}
