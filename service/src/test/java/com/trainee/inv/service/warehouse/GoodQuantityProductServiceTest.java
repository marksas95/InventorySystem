package com.trainee.inv.service.warehouse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
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
public class GoodQuantityProductServiceTest {

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
		warehouse.setAddress("Passi city");
		warehouse.setDescription("bagsak presyo");
		warehouse.setName("Blob");
		List<GoodQuantityProduct> list = new ArrayList<GoodQuantityProduct>();
//		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(11);
//		GoodQuantityProduct goodQuantityProduct2 = goodQuantityProductService.findById(12);
//		list.add(goodQuantityProduct);
//		list.add(goodQuantityProduct2);
		warehouse.setGoodQuantityProducts(list);
		warehouseService.create(warehouse);
	}
	
	@Test
	@Ignore
	public void updateTest() {
		Warehouse warehouse = warehouseService.findById(10);
//		warehouse.setName("SteelTecssh");
//		warehouse.setAddress("Cebu Citsyss");
//		warehouse.setDescription("Stseel sSets");
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(12);
		GoodQuantityProduct goodQuantityProduct2 = goodQuantityProductService.findById(11);
		System.out.println(goodQuantityProduct);
		System.out.println(goodQuantityProduct2);
		goodQuantityProducts.add(goodQuantityProduct);

		warehouse.setGoodQuantityProducts(goodQuantityProducts);
		warehouseService.update(warehouse);
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		warehouseService.delete(17);
	}
	
	@Test
	@Ignore
	public void findById() {
		Warehouse warehouse = warehouseService.findById(1);
		System.out.println(warehouse);
	}
	
}
