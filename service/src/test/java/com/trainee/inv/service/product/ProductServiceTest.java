package com.trainee.inv.service.product;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.service.category.CategoryService;
import com.trainee.inv.service.supplier.SupplierService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SupplierService supplierService;

	@Test
	@Ignore
	public void createProductTest() {
		Category category = categoryService.findByName("blossb");
		Supplier supplier = supplierService.findByName("jigs");
		Product product = new Product();

		product.setName("admin");
		product.setCategory(category);
		product.setSupplier(supplier);
		product.setDescription("Cannot be dsetermineds");
		product.setItemCode("0002222ss");
		product.setMinimumStocks(20);
		product.setRemarks("wala langss");
		product.setSerialNumber("0000000000000ss");
		product.setActive(true);
		
		product.setUnitOfMeasurement("PCSss");
		product.setVatable(false);
		
		Product createdProduct = productService.create(product);
		System.out.println(createdProduct);
	}
	
	@Test
	@Ignore
	public void createProductThatAlreadyExistsTest() {
		Category category = categoryService.findByName("blossb");
		Supplier supplier = supplierService.findByName("jigs");
		Product product = new Product();


		product.setCategory(category);
		product.setSupplier(supplier);
		product.setDescription("Cannot be determineds");
		product.setItemCode("0002222s");
		product.setMinimumStocks(20);
		product.setRemarks("wala langs");
		product.setSerialNumber("0000000000000s");
		product.setActive(false);
		
		product.setUnitOfMeasurement("PCSs");
		product.setVatable(false);
		
		Product createdProduct = productService.create(product);
		System.out.println(createdProduct);
	}
	
	@Test
//	@Ignore
	public void updateProductTest() {
		
		Product product = new Product();
		product.setId(134);
		product.setName("www");
		product.setDescription("Whole System");
		product.setItemCode("pppp");
		product.setMinimumStocks(30);
		product.setRemarks("wala lang");
		product.setSerialNumber("0000000000000s");
		product.setUnitOfMeasurement("PCSs");
		product.setVatable(false);
		productService.update(product);
	}
}
