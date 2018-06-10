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
//	@Ignore
	public void createTest() {
		Optional<Product> findById = productRepository.findById(15);
		Product product = findById.get();
		int quantity = 60;
		GoodQuantityProduct save = goodQuantityProductService.create(product, quantity);
		Assert.notNull(save, "Return object is null!");
		System.out.println(save);
	}
	

}
