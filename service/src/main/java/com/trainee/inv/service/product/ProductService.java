package com.trainee.inv.service.product;

import java.util.List;

import com.trainee.inv.repository.product.Product;

public interface ProductService {

	List<Product> findAll();
	
	Product findByName(String name);

}
