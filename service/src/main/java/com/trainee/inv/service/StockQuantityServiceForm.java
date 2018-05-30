package com.trainee.inv.service;

import com.trainee.inv.repository.warehouse.Warehouse;

public interface StockQuantityServiceForm {

	void stockIn(int warehouseId,int goodQuantityProductId, int quantity);

	void stockOut(int warehouseId,int goodQuantityProductId, int quantity);
	
	void transferStocks(int warehouseIdFrom, int warehouseIdTo, int goodQuantityProductIdFrom, int goodQuantityProductIdTo, int quantity);

	void reconcileProduct(int warehouseId, int goodQuantityProductId, int physicalQuantity);
}
