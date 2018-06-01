package com.trainee.inv.service;

import java.util.List;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;

public interface StockQuantityService {

	void stockInGoodQuantityProduct(int warehouseId,int ProductId, int quantity);

	void stockOutGoodQuantityProduct(int warehouseId,int goodQuantityProductId, int quantity);
	
	void stockInDamageQuantityProduct(int warehouseIdFrom, int warehouseIdTo, int damageQuantityProductId, int quantity);

	void stockOutDamageQuantityProduct(int warehouseId,int goodQuantityProductId, int quantity);
	
	void transferStocks(int warehouseIdFrom, int warehouseIdTo, int goodQuantityProductIdFrom, int goodQuantityProductIdTo, int quantity);

	void reconcileProduct(int warehouseId, int goodQuantityProductId, int physicalQuantity);
	
	List<GoodQuantityProduct> findByGoodQuantityProductThatReachedMinimumStocks();
	
	List<GoodQuantityProduct> sortMinimumStockByProductDescription();
}
