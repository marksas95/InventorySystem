package com.trainee.inv.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.repository.warehouse.WarehouseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseRepositoryTest {

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Test
	public void createWarehouseTest() {
		Warehouse createWarehouse = new Warehouse();
		createWarehouse.setName("Skilla");
		Warehouse saveWarehouse = warehouseRepository.save(createWarehouse);
		System.out.println(saveWarehouse);
	}
	@Test
	@Ignore
	public void findByNameTest() {
		Warehouse findByName = warehouseRepository.findByName("Skilla");
		System.out.println(findByName);
	}
}
