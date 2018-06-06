package com.trainee.inv.service.goodquantityproduct;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProductRepository;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;

@Service
class GoodQuantityProductServiceImpl implements GoodQuantityProductService {

	@Autowired
	GoodQuantityProductRepository goodQuantityRepository;

	public GoodQuantityProductServiceImpl() {
	}

	public GoodQuantityProductServiceImpl(GoodQuantityProductRepository goodQuantityRepository) {
		this.goodQuantityRepository = goodQuantityRepository;
	}

	@Override
	public GoodQuantityProduct create(Product product, int quantity) {
		GoodQuantityProduct goodQuantityProduct = new GoodQuantityProduct();
		goodQuantityProduct.setProduct(product);
		goodQuantityProduct.setQuantity(quantity);
		return goodQuantityRepository.save(goodQuantityProduct);

	}

	@Override
	public GoodQuantityProduct updateQuantity(int goodQuantityProductId, int quantity) {
		GoodQuantityProduct goodQuantityProduct = findById(goodQuantityProductId);
		goodQuantityProduct.setQuantity(quantity);
		return goodQuantityRepository.save(goodQuantityProduct);
	}

	@Override
	public GoodQuantityProduct stockIn(int goodQuantityProductId, int quantity) {
		GoodQuantityProduct goodQuantityProduct = findById(goodQuantityProductId);
		int initialQuantity = goodQuantityProduct.getQuantity();
		initialQuantity += quantity;
		return updateQuantity(goodQuantityProductId, initialQuantity);
	}

	@Override
	public GoodQuantityProduct stockOut(int goodQuantityProductId, int quantity) {
		GoodQuantityProduct goodQuantityProduct = findById(goodQuantityProductId);
		int initialQuantity = goodQuantityProduct.getQuantity();
		initialQuantity -= quantity;
		return updateQuantity(goodQuantityProductId, initialQuantity);
	}

	@Override
	public List<GoodQuantityProduct> findByProductId(int id) {
		return goodQuantityRepository.findByProductId(id);
	}

	@Override
	public GoodQuantityProduct findById(int id) {
		Optional<GoodQuantityProduct> optional = goodQuantityRepository.findById(id);
		return optional.get();
	}

	@Override
	public void delete(int id) {
		goodQuantityRepository.deleteById(id);
	}

	@Override
	public List<GoodQuantityProduct> findAll() {
		return goodQuantityRepository.findAll();
	}

}
