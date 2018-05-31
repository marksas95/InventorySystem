package com.trainee.inv.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	@Ignore
	public void transferStockTest() {
		stockQuantityServiceForm.transferStocks(9, 10, 7, 11, 75);
	}
	
	@Test
//	@Ignore
	public void reconcileProductTest() {
		stockQuantityServiceForm.reconcileProduct(9, 7, 80);
	}
}
