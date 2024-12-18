package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
	List<Product> findAll();

	void add(Product product);

	Product findById(int id);

	void update(Product product);

	void remove(int id);
}
