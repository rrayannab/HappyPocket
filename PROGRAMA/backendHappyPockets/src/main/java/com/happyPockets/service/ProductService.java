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

	public ProductService() {
		productList = new ArrayList<>();

		// Leer el archivo de Excel y cargar los productos
		try (FileInputStream fis = new FileInputStream(new File(rutaArchivo))) {
			// Abrir el libro de Excel
			Workbook libro = WorkbookFactory.create(fis);

			Sheet hoja = libro.getSheetAt(0);

			int i = 0;

			for (Row fila : hoja) {
				if (fila.getRowNum() < 2)
					continue;
				else if (fila.getCell(0) == null)
					break;

				try {
					String name = fila.getCell(0).getStringCellValue();
					String brand = fila.getCell(1).getStringCellValue();
					String cat = fila.getCell(2).getStringCellValue();
					ShopPrice shopPrices = new ShopPrice(fila.getCell(3).getNumericCellValue(),
							fila.getCell(4).getNumericCellValue(), fila.getCell(5).getNumericCellValue());
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

	/**
	 * Obtiene un producto por su id
	 * @param id Id del producto
	 * @return Producto
	 */
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

	/**
	 * Obtiene la lista de productos
	 * @return Lista de productos
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * Ordena una lista de productos según el criterio de orden.
	 * "asc" ordena alfabéticamente de forma ascendente.
	 * "desc" ordena alfabéticamente de forma descendente.
	 * "priceAsc" ordena por precio de forma ascendente.
	 * "priceDesc" ordena por precio de forma descendente.
	 * @param products Lista de productos
	 * @param order Criterio de orden
	 * @return Lista de productos ordenada
	 */
	public static List<Product> getProductListOrder(List<Product> products, String order) {
		if (products == null || order == null)
			return null;
		if (order.equalsIgnoreCase("asc")) {
			products.sort(Comparator.comparing(Product::getName));
			return products;
		} else if (order.equalsIgnoreCase("desc")) {
			products.sort(Comparator.comparing(Product::getName).reversed());
			return products;
		} else if (order.equalsIgnoreCase("priceAsc")) {
			products.sort(Product.comparadorPorPrecio);
			return products;
		} else if (order.equalsIgnoreCase("priceDesc")) {
			products.sort(Product.comparadorPorPrecio);
			Collections.reverse(products);
			return products;
		} else
			return null;
	}

	/**
	 * Obtiene los todos productos de un rango de precios
	 * @param productListIn Lista de productos
	 * @param priceFrom Precio mínimo
	 * @param priceTo Precio máximo
	 * @return Lista de productos en el rango de precios
	 */
	public static List<Product> getProductPriceRange(
			List<Product> productListIn,
			@RequestParam Optional<Double> priceFrom,
			@RequestParam Optional<Double> priceTo) {
		if (productListIn == null || (priceFrom.isPresent() && priceFrom.get() < 0)
				|| (priceTo.isPresent() && priceTo.get() < 0))
			return null;
		if (priceFrom.isPresent() && priceTo.isPresent() && priceFrom.get() > priceTo.get())
			return null;
		if (priceFrom.isPresent()) {
			productListIn = productListIn.stream()
					.filter(product -> product.getShopPrices().getBestPrice() >= priceFrom.get()).toList();
		}
		if (priceTo.isPresent()) {
			productListIn = productListIn.stream()
					.filter(product -> product.getShopPrices().getBestPrice() <= priceTo.get()).toList();
		}

		return productListIn;
	}

	/**
	 * Obtiene los todos productos de una categoría
	 * @param productListIn Lista de productos
	 * @param category Categoría
	 * @return Lista de productos de la categoría
	 */
	public static List<Product> getProductCategory(List<Product> productListIn,
			@RequestParam Optional<String> category) {
		if (productListIn == null || category == null || (category.isPresent() && category.get().equals("all")))
			return productListIn;
		if (category.isPresent()) {
			productListIn = productListIn.stream().filter(product -> product.getCat().equalsIgnoreCase(category.get()))
					.toList();
		}
		return productListIn;
	}

	/**
	 * Obtiene los todos productos de una marca
	 * @param productListIn Lista de productos
	 * @param brand Marca
	 * @return Lista de productos de la marca
	 */
	public static List<Product> getProductSearch(List<Product> productListIn, @RequestParam Optional<String> product) {
		if (productListIn == null)
			return productListIn;
		if (product != null && product.isPresent()) {
			productListIn = productListIn.stream()
					.filter(product1 -> product1.getName().toLowerCase().contains(product.get().toLowerCase()))
					.toList();
		}
		return productListIn;
	}

	/**
	 * Obtiene las categorías de los productos
	 * @return Lista de categorías
	 */
	public List<String> getCategories() {
		List<String> categories = new ArrayList<>();
		for (Product product : productList) {
			if (!categories.contains(product.getCat())) {
				categories.add(product.getCat());
			}
		}
		Collections.sort(categories);
		return categories;
	}

	/**
	 * Obtiene las marcas de los productos
	 * @return Lista de marcas
	 */
	public List<String> getBrands() {
		List<String> brands = new ArrayList<>();
		for (Product product : productList) {
			if (!brands.contains(product.getBrand())) {
				brands.add(product.getBrand());
			}
		}
		return brands;
	}
}
