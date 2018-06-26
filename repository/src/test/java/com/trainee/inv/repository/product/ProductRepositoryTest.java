package com.trainee.inv.repository.product;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.category.CategoryRepository;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;
import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.repository.supplier.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository	categoryRepository;
	@Autowired
	SupplierRepository supplierRepository;
	
	@Test
	@Ignore
	public void createProductTest() {
		Optional<Category> category2 = categoryRepository.findById(93);
		Category category3 = category2.get();
		Optional<Supplier> supplier2 = supplierRepository.findById(94);
		Supplier supplier = supplier2.get();
		Product product = new Product();


		product.setCategory(category3);
		product.setSupplier(supplier);
		product.setDescription("Cannot be determineds");
		product.setItemCode("0002222s");
		product.setMinimumStocks(20);
		product.setRemarks("wala langs");
		product.setSerialNumber("0000000000000s");
		product.setActive(false);
		
		product.setUnitOfMeasurement("PCSs");
		product.setVatable(false);
		Product productSave = productRepository.save(product );
		Assert.isTrue(product.equals(productSave));
	}
	
	@Test
//	@Ignore
	public void deleteProductTest() {
		productRepository.deleteById(7);
	}
	
	@Test
	@Ignore
	public void findByIsActiveTrueTest() {
		List<Product> list = productRepository.findByIsActive(true);
		System.out.println(list);
		Assert.notEmpty(list);
	}
	
	@Test
	@Ignore
	public void findByIsActiveFlaseTest() {
		List<Product> list = productRepository.findByIsActive(false);
		System.out.println(list);
		Assert.notEmpty(list);
	}
	
	@Test
	@Ignore
	public void findByCategoryIdTest() {
		List<Product> list = productRepository.findByCategoryId(93);
		System.out.println(list);
		Assert.notEmpty(list);
	}
	
	@Test
	@Ignore
	public void findBySupplierTest() {
		List<Product> list = productRepository.findBySupplierId(94);
		System.out.println(list);
		Assert.notEmpty(list);
	}
	
}
