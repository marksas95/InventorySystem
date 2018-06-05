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

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.warehouse.WarehouseService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockQuantityServiceFornTest {

	@Autowired
	private StockQuantityService stockQuantityServiceForm;
	@Autowired
	private GoodQuantityProductService goodQuantityProductService;
	@Autowired
	private WarehouseService warehouseService;
	
	@Test
//	@Ignore
	public void stockInTest() {

//		stockQuantityServiceForm.stockInGoodQuantityProduct(20, 3, 40);

		int warehouseId = 14;
		int productId = 4;
		int quantity = 500;
		Warehouse warehouse1 = warehouseService.findById(warehouseId);
		Optional<GoodQuantityProduct> optional1 = warehouse1.getGoodQuantityProducts().stream().filter(w -> w.getProduct().getId()==productId).findFirst();
		int quantity1 = optional1.get().getQuantity();
		
		stockQuantityServiceForm.stockInGoodQuantityProduct(warehouseId, productId, quantity);
		Warehouse warehouse = warehouseService.findById(warehouseId);
		Optional<GoodQuantityProduct> optional2 = warehouse.getGoodQuantityProducts().stream().filter(w -> w.getProduct().getId()==productId).findFirst();
		
		Assert.isTrue((quantity1+quantity)==optional2.get().getQuantity());
	}
	
	@Test
	@Ignore
	public void stockOutTest() {
		stockQuantityServiceForm.stockOutGoodQuantityProduct(14, 4, 300);
	}
	
	@Test
	@Ignore
	public void transferStockTest() {

		stockQuantityServiceForm.transferStocks(13,14, 4, 50);

	}
	
	@Test
	@Ignore
	public void stockInDamageQuantityProduct() {
		stockQuantityServiceForm.stockInDamageQuantityProduct(13, 14, 4, 60);
	}
	
	@Test
	@Ignore
	public void reconcileProductTest() {
		stockQuantityServiceForm.reconcileProduct(9, 7, 80);
	}
	
	@Test
	@Ignore
	public void sortMinimumStockByDescription() {
		List<GoodQuantityProduct> sortMinimumStockByProductDescription = stockQuantityServiceForm.sortMinimumStockByProductDescription();
		System.out.println(sortMinimumStockByProductDescription);
	}
	
}
