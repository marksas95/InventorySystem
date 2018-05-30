package com.trainee.inv.service.supplier;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.supplier.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceTest {

	@Autowired
	private SupplierService supplierService;

	@Test
//	@Ignore
	public void createTest() {
		String name = "Clothing1 Trading";
		Supplier supplierCreate = supplierService.create(name);
		System.out.println(supplierCreate);
		Assert.notNull(supplierCreate);
		Assert.isTrue(supplierCreate.getName().equals(name));
	}

	@Test
	@Ignore
	public void findByNameTest() {
		Supplier supplierFindByName = supplierService.findByName("jigs");
		Assert.notNull(supplierFindByName);
		
	}

	@Test
	@Ignore
	public void findAllTest() {
		List<Supplier> suppliers = supplierService.findAll();
		Assert.notEmpty(suppliers);
		
	}
	@Test
	@Ignore
	public void updateTest() {
		Supplier updatedService = supplierService.update(2, "holly");
		Assert.isTrue(updatedService.getName().equals("holly"));
		System.out.println(updatedService);
	}
	@Test
	@Ignore
	public void deleteTest() {
		supplierService.delete(1);
		Assert.isNull(supplierService.findByName("holly"));
		
	}
	
}
