package com.trainee.inv.service.supplier;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.supplier.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierServiceTest {

	@Autowired
	private SupplierService supplierService;

	@Test
	@Ignore
	public void createTest() {
		String name = "Lazada";
		Supplier supplierCreate = supplierService.create(name);
		System.out.println(supplierCreate);
		Assert.notNull(supplierCreate);
		Assert.isTrue(supplierCreate.getName().equals(name));
	}
	
	@Test
	@Ignore
	public void createSupplierAlreadyExist() {
		try {
			Supplier supplier = supplierService.create("King");
			System.out.println(supplier);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Supplier Name Already Exist"));
		}
	}

	@Test
	@Ignore
	public void createCategoryWithInvalidNameTest() {
		try {
			Supplier supplier = supplierService.create("Lazada");
			System.out.println(supplier);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Supplier Name Already Exist"));
		}
	}

	@Test
	@Ignore
	public void findByNameTest() {
		Supplier supplierFindByName = supplierService.findByName("Lazada");
		Assert.notNull(supplierFindByName);
	}
	
	@Test
	@Ignore
	public void findByNameTestWithNoNameInDatabase() {
		Supplier supplierFindByName = supplierService.findByName("Lazadasss");
		Assert.isNull(supplierFindByName,"this is null.");

	}
	
	@Test
	@Ignore
	public void failedFindByNameTest() {
		Supplier supplier = supplierService.findByName("tta");
		Assert.isNull(supplier);
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
		
		String name = "Worthwhile";
		int id = 7;
		Supplier updatedService = supplierService.update(id, name);
		Assert.isTrue(updatedService.getName().equals(name));
		System.out.println(updatedService);
	}

	
	@Test
//	@Ignore
	public void supplierNameExistUpdateTest() {
		try {
			Supplier update = supplierService.update(7, "Worthwhile");
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Supplier Name Already Exists"));
		}
		
	}
	
	@Test
	@Ignore
	public void deleteTest() {
		supplierService.delete(1);
		Assert.isNull(supplierService.findByName("holly"));
		
	}
	
	
	@Test
	@Ignore
	public void failedDeleteTest() {
		
		try {
			supplierService.delete(2);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Invalid operation, ID does not exist!"));
		}
		
	}

}
