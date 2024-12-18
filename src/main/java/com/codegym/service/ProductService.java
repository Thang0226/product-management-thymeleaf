package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
	private static final Map<Integer, Product> products;

	static {
		products = new LinkedHashMap<>();
		products.put(1, new Product(1, "Laptop", 1200.99, "High-performance laptop with 16GB RAM and 512GB SSD."));
		products.put(2, new Product(2, "Smartphone", 799.49, "Latest smartphone with a 6.5-inch display and 128GB storage."));
		products.put(3, new Product(3, "Headphones", 199.99, "Wireless noise-cancelling headphones with 30-hour battery life."));
		products.put(4, new Product(4, "Smartwatch", 299.99, "Fitness-focused smartwatch with heart rate and GPS tracking."));
		products.put(5, new Product(5, "Camera", 649.00, "Mirror-less camera with 24.2MP sensor and 4K video recording."));
		products.put(6, new Product(6, "Gaming Console", 499.99, "Next-gen gaming console with ultra-fast SSD and 4K graphics."));
	}

	@Override
	public List<Product> findAll() {
		return new ArrayList<>(products.values());
	}

	@Override
	public void add(Product product) {
		products.put(product.getId(), product);
	}

	@Override
	public Product findById(int id) {
		return products.get(id);
	}

	@Override
	public void update(Product product) {
		products.put(product.getId(), product);
	}

	@Override
	public void remove(int id) {
		products.remove(id);
	}
}
