package com.trainee.inv.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockQuantityServiceFornTest {

	@Autowired
	StockQuantityService stockQuantityServiceForm;
	
	@Test
	@Ignore
	public void stockInTest() {
		stockQuantityServiceForm.stockInGoodQuantityProduct(10, 12, 500);
	}
	
	@Test
	@Ignore
	public void stockOutTest() {
		stockQuantityServiceForm.stockOutGoodQuantityProduct(10, 12, 300);
	}
	
	@Test
//	@Ignore
	public void transferStockTest() {
		stockQuantityServiceForm.transferStocks(20, 23, 1, 21, 23);
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
	public void sortMinimumStockByDescription() {
		List<GoodQuantityProduct> sortMinimumStockByProductDescription = stockQuantityServiceForm.sortMinimumStockByProductDescription();
		System.out.println(sortMinimumStockByProductDescription);
	}
	
}
