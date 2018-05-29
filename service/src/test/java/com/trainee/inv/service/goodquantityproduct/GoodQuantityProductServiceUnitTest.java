package com.trainee.inv.service.goodquantityproduct;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProductRepository;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;

public class GoodQuantityProductServiceUnitTest {

	private GoodQuantityProductService goodQuantityProductService;

	@Mock
	private GoodQuantityProductRepository goodQuantityRepository;

	Product product = new Product();
	Warehouse warehouse = new Warehouse();
	int quantity = 10;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		goodQuantityProductService = new GoodQuantityProductServiceImpl(goodQuantityRepository);
		product.setId(100);
		warehouse.setName("blob");
	}

	@Test
	public void createTest() {
		Mockito.when(goodQuantityRepository.save(Mockito.any(GoodQuantityProduct.class)))
				.thenReturn(getGoodQuantityProduct());
		GoodQuantityProduct quantityProduct = goodQuantityProductService.create(product, quantity);
		Assert.notNull(quantityProduct, "Null");
		Mockito.verify(goodQuantityRepository).save(Mockito.any(GoodQuantityProduct.class));
		System.out.println(quantityProduct);
	}

	private GoodQuantityProduct getGoodQuantityProduct() {
		GoodQuantityProduct goodQuantityProduct = new GoodQuantityProduct();
		goodQuantityProduct.setId(10);
		goodQuantityProduct.setQuantity(quantity);
		goodQuantityProduct.setProduct(product);
		return goodQuantityProduct;
	}
}
