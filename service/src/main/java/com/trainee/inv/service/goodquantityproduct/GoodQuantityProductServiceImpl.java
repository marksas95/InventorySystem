package com.trainee.inv.service.goodquantityproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityRepository;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;

@Service
class GoodQuantityProductServiceImpl implements GoodQuantityProductService{

	
	@Autowired
	GoodQuantityRepository goodQuantityRepository;
	
	@Override
	public GoodQuantityProduct create(Product product, Warehouse warehouse, int quantity) {
		GoodQuantityProduct goodQuantityProduct = new GoodQuantityProduct();
		goodQuantityProduct.setProduct(product);
		goodQuantityProduct.setQuantity(quantity);
		
		return goodQuantityRepository.save(goodQuantityProduct);
		
	}

}
