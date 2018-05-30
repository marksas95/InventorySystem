package com.trainee.inv.service;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;

public interface StatusServiceForm {

	Product setProductToActive(int productId);

	Product setProductToInActive(int productId);
	
	Warehouse setWarehouseToActive(int warehouseId);
	
	Warehouse setWarehouseToInActive(int warehouseId);
}
