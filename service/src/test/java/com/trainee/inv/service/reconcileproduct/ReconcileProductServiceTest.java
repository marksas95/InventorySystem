package com.trainee.inv.service.reconcileproduct;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.warehouse.WarehouseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReconcileProductServiceTest {

	@Autowired
	ReconcileProductService reconcileProductService;
	@Autowired
	GoodQuantityProductService goodQuantityProductService;
	@Autowired
	WarehouseService warehouseService;
	
	@Test
//	@Ignore
	public void createTest() {
	 ReconcileProduct reconcileProduct = new ReconcileProduct();	
	 reconcileProduct.setGoodQuantityProduct(goodQuantityProductService.findById(1));
	 reconcileProduct.setWarehouse(warehouseService.findById(20));
	 reconcileProduct.setPhysicalCount(100);
	 reconcileProduct.setSystemCount(101);
	 reconcileProductService.create(reconcileProduct);
	}
	
}
