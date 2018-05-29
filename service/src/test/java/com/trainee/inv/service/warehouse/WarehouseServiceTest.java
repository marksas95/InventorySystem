package com.trainee.inv.service.warehouse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseServiceTest {

	@Autowired
	WarehouseService warehouseService;
	@Autowired
	GoodQuantityProductService goodQuantityProductService;
	@Autowired
	ProductService productService;
	
	@Test
//	@Ignore
	public void createTest() {
		Warehouse warehouse = new Warehouse();
		warehouse.setActive(true);
		warehouse.setAddress("Iloilo Citys");
		warehouse.setDescription("Tubigans");
		warehouse.setName("marksas55");
		List<GoodQuantityProduct> list = new ArrayList<>();
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(144);
		list.add(goodQuantityProduct);
		warehouse.setGoodQuantityProducts(list);
		warehouseService.create(warehouse);
	}
	
}
