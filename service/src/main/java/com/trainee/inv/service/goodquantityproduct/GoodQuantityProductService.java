 package com.trainee.inv.service.goodquantityproduct;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;

public interface GoodQuantityProductService {

	GoodQuantityProduct create(Product product, Warehouse warehouse, int quantity);

	
}
