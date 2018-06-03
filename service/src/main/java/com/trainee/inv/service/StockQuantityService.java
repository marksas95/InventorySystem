package com.trainee.inv.service;

import java.util.List;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;

public interface StockQuantityService {

	void stockInGoodQuantityProduct(int warehouseId, int productId, int quantity);

	void stockOutGoodQuantityProduct(int warehouseId, int productId, int quantity);

	void stockInDamageQuantityProduct(int warehouseIdFrom, int warehouseIdTo, int productId, int quantity);

	void stockOutDamageQuantityProduct(int warehouseId, int productId, int quantity);

	void transferStocks(int warehouseIdFrom, int warehouseIdTo,int productId, int quantity);

	void reconcileProduct(int warehouseId, int productId, int physicalQuantity);

	List<GoodQuantityProduct> findByGoodQuantityProductThatReachedMinimumStocks();

	List<GoodQuantityProduct> sortMinimumStockByProductDescription();
}
