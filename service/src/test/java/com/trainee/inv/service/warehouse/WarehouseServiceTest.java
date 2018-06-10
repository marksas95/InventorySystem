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

import junit.framework.Assert;

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
	@Ignore
	public void createTest() {
		Warehouse warehouse = new Warehouse();
		warehouse.setActive(true);
		warehouse.setAddress("Lapaz");
		warehouse.setDescription("Lapaz Old warehouse");
		warehouse.setName("Lapaz Warehouse");
		List<GoodQuantityProduct> list = new ArrayList<GoodQuantityProduct>();
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(34);
//		GoodQuantityProduct goodQuantityProduct2 = goodQuantityProductService.findById(12);
		list.add(goodQuantityProduct);
//		list.add(goodQuantityProduct2);
		warehouse.setGoodQuantityProducts(list);
		warehouseService.create(warehouse);
	}
	
	@Test
	public void createWarehouseOnlyTest() {
		String warehouseName = "Mandur-ao";
		String address = "Mandurriao District";
		String description = "Somewhere in Mandurriao";
		boolean isActive = true;
		
		
		
	}
	
	
	
	@Test 
	@Ignore
	public void createTestNameExists() {
		Warehouse warehouse = new Warehouse();
		warehouse.setName("LIon");
		warehouse.setDescription("Old");
		warehouse.setAddress("Hibao-an");
		List<GoodQuantityProduct> list = new ArrayList<GoodQuantityProduct>();
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(1);
		list.add(goodQuantityProduct);
		warehouse.setGoodQuantityProducts(list);
		warehouseService.create(warehouse);
		Assert.assertNull(warehouse);
	}
	
	
	@Test
//	@Ignore
	public void updateTest() {
		Warehouse warehouse = warehouseService.findById(35);
		warehouse.setName("Steel Tech Warehouse");
		warehouse.setAddress("Cebu City");
		warehouse.setDescription("Steel Set");
//		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
//		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(21);
//		GoodQuantityProduct goodQuantityProduct2 = goodQuantityProductService.findById(11);
//		System.out.println(goodQuantityProduct);
//		System.out.println(goodQuantityProduct2);
//		goodQuantityProducts.add(goodQuantityProduct);

//		warehouse.setGoodQuantityProducts(goodQuantityProducts);
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
