package com.trainee.inv.service.goodquantityproduct;

import java.util.List;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;

public interface GoodQuantityProductService {

	GoodQuantityProduct create(Product product, int quantity);
	
	GoodQuantityProduct updateQuantity(int goodQuantityProductId, int quantity);
	
	GoodQuantityProduct stockIn(int goodQuantityProductId, int quantity);
	
	GoodQuantityProduct stockOut(int goodQuantityProductId, int quantity);
	
	List<GoodQuantityProduct> findByProductId(int id);
	
	GoodQuantityProduct findById(int id);
	
	List<GoodQuantityProduct> findAll();
	
	void delete(int id);
}
