package com.trainee.inv.repository.supplier;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.repository.supplier.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierRepositoryTest {

	@Autowired
	private SupplierRepository supplierRepository;

	@Test
	@Ignore
	public void createSupplierTest() {
		
		Supplier supplier = new Supplier();
		supplier.setName("supplier1");
		Supplier saveSupplier = supplierRepository.save(supplier );
		System.out.println(saveSupplier);
		
	}
	@Test
	public void findByNameTest() {
		Supplier findByNameSupplier = supplierRepository.findByName("Kingsoft");
		System.out.println(findByNameSupplier);
	}
	
}
