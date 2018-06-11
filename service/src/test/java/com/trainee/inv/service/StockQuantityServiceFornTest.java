package com.trainee.inv.service;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.reconcileproduct.ReconcileProductService;
import com.trainee.inv.service.warehouse.WarehouseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockQuantityServiceFornTest {

	@Autowired
	private StockQuantityService stockQuantityServiceForm;
	@Autowired
	private WarehouseService warehouseService;
	@Autowired
	private ReconcileProductService reconcileProductService;

	@Test
//	@Ignore
	public void stockInGoodQuantityTest() {

		int warehouseId = 14;
		int productId = 4;
		int quantity = 500;

		int initialGoodQuantityOfWarehouse = getGoodQuantityOfWarehouse(warehouseId, productId);
		stockQuantityServiceForm.stockInGoodQuantityProduct(warehouseId, productId, quantity);
		int finalGoodQuantityOfWarehouse = getGoodQuantityOfWarehouse(warehouseId, productId);

		Assert.isTrue((initialGoodQuantityOfWarehouse + quantity) == finalGoodQuantityOfWarehouse);
	}

	@Test
	@Ignore
	public void stockOutGoodQuantityTest() {

		int warehouseId = 14;
		int productId = 4;
		int quantity = 500;

		int initialGoodQuantityOfWarehouse = getGoodQuantityOfWarehouse(warehouseId, productId);
		stockQuantityServiceForm.stockOutGoodQuantityProduct(warehouseId, productId, quantity);
		int finalGoodQuantityOfWarehouse = getGoodQuantityOfWarehouse(warehouseId, productId);
		Assert.isTrue((initialGoodQuantityOfWarehouse - quantity) == finalGoodQuantityOfWarehouse);
	}

	private int getGoodQuantityOfWarehouse(int warehouseId, int productId) {
		Warehouse warehouse1 = warehouseService.findById(warehouseId);
		Optional<GoodQuantityProduct> optional1 = warehouse1.getGoodQuantityProducts().stream()
				.filter(w -> w.getProduct().getId() == productId).findFirst();
		return optional1.get().getQuantity();
	}

	@Test
	@Ignore
	public void stockOutTestWithInvalidQuantityGreaterThanPresentQuantity() {

		int warehouseId = 14;
		int productId = 4;
		int quantity = 50000;

		try {
			stockQuantityServiceForm.stockOutGoodQuantityProduct(warehouseId, productId, quantity);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Stock out quantity is greater than the available number of items"));
		}

	}

	@Test
	@Ignore
	public void transferStockTest() {

		int warehouseIdFrom = 13;
		int warehouseIdTo = 14;
		int productId = 4;
		int quantity = 50;

		int initialQuantityFrom = getGoodQuantityOfWarehouse(warehouseIdFrom, productId);
		int initialQuantityTo = getGoodQuantityOfWarehouse(warehouseIdTo, productId);

		stockQuantityServiceForm.transferStocks(warehouseIdFrom, warehouseIdTo, productId, quantity);

		int finalQuantityFrom = getGoodQuantityOfWarehouse(warehouseIdFrom, productId);
		int finalQuantityTo = getGoodQuantityOfWarehouse(warehouseIdTo, productId);

		Assert.isTrue((initialQuantityFrom - quantity) == finalQuantityFrom);
		Assert.isTrue((initialQuantityTo + quantity) == finalQuantityTo);
	}

	@Test
	@Ignore
	public void transferStockTestWithGreaterAmountOfQuantityToTransfer() {

		int warehouseIdFrom = 13;
		int warehouseIdTo = 14;
		int productId = 4;
		int quantity = 5000000;

		try {
			stockQuantityServiceForm.transferStocks(warehouseIdFrom, warehouseIdTo, productId, quantity);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Stock out quantity is greater than the available number of items"));
		}

	}

	@Test
	@Ignore
	public void stockInDamageQuantityProduct() {

		int warehouseIdFrom = 13;
		int warehouseIdTo = 14;
		int productId = 4;
		int quantity = 60;

		int initialGoodQuantityOfWarehouseFrom = getGoodQuantityOfWarehouse(warehouseIdFrom, productId);
		int initialDamageQuantityOfWarehouseTo = getDamageQuantityOfWarehouse(warehouseIdTo, productId);

		stockQuantityServiceForm.stockInDamageQuantityProduct(warehouseIdFrom, warehouseIdTo, productId, quantity);

		int finalGoodQuantityOfWarehouseFrom = getGoodQuantityOfWarehouse(warehouseIdFrom, productId);
		int finalDamageQuantityOfWarehouseTo = getDamageQuantityOfWarehouse(warehouseIdTo, productId);

		Assert.isTrue((initialGoodQuantityOfWarehouseFrom - quantity) == finalGoodQuantityOfWarehouseFrom);
		Assert.isTrue((initialDamageQuantityOfWarehouseTo + quantity) == finalDamageQuantityOfWarehouseTo);
	}

	@Test
	@Ignore
	public void stockInDamageQuantityProductWithLargerAmountOfQuantityThanGoodStock() {

		int warehouseIdFrom = 13;
		int warehouseIdTo = 14;
		int productId = 4;
		int quantity = 100000;

		try {
			stockQuantityServiceForm.stockInDamageQuantityProduct(warehouseIdFrom, warehouseIdTo, productId, quantity);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Stock out quantity is greater than the available number of items"));
		}
	}

	private int getDamageQuantityOfWarehouse(int warehouseId, int productId) {
		Warehouse warehouse1 = warehouseService.findById(warehouseId);
		Optional<DamageQuantityProduct> optional1 = warehouse1.getDamageQuantityProducts().stream()
				.filter(w -> w.getProduct().getId() == productId).findFirst();
		return optional1.get().getQuantity();
	}

	@Test
	@Ignore
	public void stockOutDamageQuantityProduct() {

		int warehouseId = 14;
		int productId = 4;
		int quantity = 50;

		int initialDamageQuantityOfWarehouse = getDamageQuantityOfWarehouse(warehouseId, productId);
		stockQuantityServiceForm.stockOutDamageQuantityProduct(warehouseId, productId, quantity);
		int finalDamageQuantityOfWarehouseTo = getDamageQuantityOfWarehouse(warehouseId, productId);

		Assert.isTrue((initialDamageQuantityOfWarehouse - quantity) == finalDamageQuantityOfWarehouseTo);
	}

	@Test
	 @Ignore
	public void stockOutDamageQuantityProductWithGreaterAmountOfQuantityThatPresent() {

		int warehouseId = 14;
		int productId = 4;
		int quantity = 50000000;
		try {
			stockQuantityServiceForm.stockOutDamageQuantityProduct(warehouseId, productId, quantity);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Stock out quantity is greater than the available number of items"));
		}
	}

	@Test
//	@Ignore
	public void reconcileProductTest() {
		int warehouseId = 13;
		int productId = 4;
		int physicalQuantity = 100;

		int initialGoodQuantity = getGoodQuantityOfWarehouse(warehouseId, productId);
		stockQuantityServiceForm.reconcileProduct(warehouseId, productId, physicalQuantity);
		int finalGopodQuantity = getGoodQuantityOfWarehouse(warehouseId, productId);
		int systemCount = getSystemCountOfReconcileProduct(warehouseId);

		Assert.isTrue(finalGopodQuantity == physicalQuantity);
		Assert.isTrue(initialGoodQuantity == systemCount);
	}

	private int getSystemCountOfReconcileProduct(int warehouseId) {
		List<ReconcileProduct> reconciledProducts = reconcileProductService.findByWarehouseId(warehouseId);
		int size = reconciledProducts.size();
		ReconcileProduct reconcileProduct = reconciledProducts.get(size-1);
		int systemCount = reconcileProduct.getSystemCount();
		return systemCount;
	}

	@Test
	@Ignore
	public void sortMinimumStockByDescription() {
		List<GoodQuantityProduct> sortMinimumStockByProductDescription = stockQuantityServiceForm
				.sortMinimumStockByProductDescription();
		System.out.println(sortMinimumStockByProductDescription);
	}

}
