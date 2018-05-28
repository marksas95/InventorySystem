package com.trainee.inv.service.product;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.product.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	public void findAllProductsTest() {
		List<Product> findProducts = productService.findAll();
		Assert.notNull(findProducts);
	}

	@Test
	@Ignore
	public void findByName() {
		Product findByNameProduct = productService.findByName("Howie");
		Assert.notNull(findByNameProduct);
	}

}
