package com.trainee.inv.service.product;

import java.util.List;

import com.trainee.inv.repository.product.Product;

public interface ProductService {

	Product create(Product product);

	Product update(Product product);

	Product findById(int id);

	List<Product> findByIsActive(boolean isActive);

	List<Product> findByCategoryId(int id);

	List<Product> findBySupplierId(int id);

	List<Product> findAll();

	List<Product> searchByItemCode(String itemCode);

	List<Product> searchByUnitOfMeasurement(String unitOfMeasurement);

	List<Product> searchByDescription(String description);

	Product findByName(String name);

	void delete(int id);

	List<Product> sortByName();

	List<Product> sortByItemCode();
	
	List<Product> sortByDescription();
	
	List<Product> sortByMinimumStock();
	
}
