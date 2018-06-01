package com.trainee.inv.service.damagequantityproduct;

import java.util.List;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.product.Product;

public interface DamageQuantityProductService {
	
	DamageQuantityProduct create(Product product, int quantity);
	
	DamageQuantityProduct updateQuantity(int damageProductQuantityId, int quantity);
	
	DamageQuantityProduct stockIn(int damageProductQuantityId, int quantity);
	
	DamageQuantityProduct stockOut(int damageProductQuantityId, int quantity);
	
	List<DamageQuantityProduct> findByProductId(int id);
	
	DamageQuantityProduct findById(int id);
	
	List<DamageQuantityProduct> findAll();
	
	void delete(int id);
}
