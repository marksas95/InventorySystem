package com.trainee.inv.service;

public interface StockQuantityService {

	void stockInGoodQuantityProduct(int warehouseId,int goodQuantityProductId, int quantity);

	void stockOutGoodQuantityProduct(int warehouseId,int goodQuantityProductId, int quantity);
	
	void stockInDamageQuantityProduct(int warehouseId,int goodQuantityProductId, int quantity);

	void stockOutDamageQuantityProduct(int warehouseId,int goodQuantityProductId, int quantity);
	
	void transferStocks(int warehouseIdFrom, int warehouseIdTo, int goodQuantityProductIdFrom, int goodQuantityProductIdTo, int quantity);

	void reconcileProduct(int warehouseId, int goodQuantityProductId, int physicalQuantity);
}
