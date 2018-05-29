package com.trainee.inv.service;

import com.trainee.inv.repository.warehouse.Warehouse;

public interface StockQuantityServiceForm {

	void stockIn(int warehouseId,int productId, int quantity);

	void stockOut(int warehouseId,int productId, int quantity);
}
