package com.trainee.inv.service.product;

import java.util.List;

import com.trainee.inv.repository.product.Product;

public interface ProductService {


	Product create(Product product);

	Product update(Product product);
	
	Product findById(int id);
	
	List<Product> findByIsActive(boolean isActive);
	
	List<Product> findAll();
	
	List<Product> searchByItemCode(String itemCode,  boolean isActive);
	
	List<Product> searchByUnitOfMeasurement(String unitOfMeasurement,  boolean isActive);
	
	List<Product> searchByDescription(String decription,  boolean isActive);
		
	Product findByName(String name);
	
	void delete(int id);

}
