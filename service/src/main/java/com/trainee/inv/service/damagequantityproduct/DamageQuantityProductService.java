package com.trainee.inv.service.damagequantityproduct;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.product.Product;

public interface DamageQuantityProductService {
	
	DamageQuantityProduct create(Product product);
	
}
