package com.trainee.inv.repository.goodquantityproduct;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.product.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodQuantityProductRepositoryTest {
	
	@Autowired
	GoodQuantityProductRepository goodQuantityProductRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
//	@Ignore
	public void createTest() {
		GoodQuantityProduct goodQuantityProduct = new GoodQuantityProduct();
		Optional<Product> optional = productRepository.findById(134);
		Product product = optional.get();
		goodQuantityProduct.setProduct(product);
		goodQuantityProductRepository.save(goodQuantityProduct);
	}
}
