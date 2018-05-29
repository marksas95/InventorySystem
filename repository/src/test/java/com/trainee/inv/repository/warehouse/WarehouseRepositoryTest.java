package com.trainee.inv.repository.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseRepositoryTest {

	@Autowired
	WarehouseRepository warehouseRepository;
	
	@Autowired
	GoodQuantityProductRepository goodQuantityProductRepository;
	
	@Test
//	@Ignore
	public void createTest() {
		Warehouse warehouse = new Warehouse();
		warehouse.setActive(true);
		warehouse.setAddress("Balabago");
		warehouse.setDescription("Blob");
		
		Optional<GoodQuantityProduct> optional = goodQuantityProductRepository.findById(158);
		GoodQuantityProduct goodQuantityProduct = optional.get();
		Optional<GoodQuantityProduct> optional2 = goodQuantityProductRepository.findById(159);
		GoodQuantityProduct goodQuantityProduct2 = optional2.get();
		
		List<GoodQuantityProduct> list = new ArrayList<>();
		list.add(goodQuantityProduct);
		list.add(goodQuantityProduct2);
		
		warehouse.setGoodQuantityProducts(list);
		
		warehouseRepository.save(warehouse);
	}
}
