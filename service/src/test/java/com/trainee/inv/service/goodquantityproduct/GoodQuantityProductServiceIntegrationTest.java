package com.trainee.inv.service.goodquantityproduct;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.repository.warehouse.WarehouseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodQuantityProductServiceIntegrationTest {
	
	
	@Autowired
	private GoodQuantityProductService goodQuantityProductService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private WarehouseRepository warehouseRepository;

	@Test
	@Ignore
	public void createTest() {
		Optional<Product> findById = productRepository.findById(137);
		Product product = findById.get();
		int quantity = 0;
		GoodQuantityProduct save = goodQuantityProductService.create(product, quantity);
		Assert.notNull(save, "Return object is null!");
		System.out.println(save);
	}
	
	@Test
	@Ignore
	public void stockInTest() {
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.stockIn(138, 150);
		Assert.isTrue(goodQuantityProduct.getQuantity() == 450);
		System.out.println(goodQuantityProduct);
	}
	
	@Test
//	@Ignore
	public void stockOutTest() {
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.stockOut(138, 350);
		Assert.isTrue(goodQuantityProduct.getQuantity() == 100);
		System.out.println(goodQuantityProduct);
	}
	
	
}
