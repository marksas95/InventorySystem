package com.trainee.inv.service.goodquantityproduct;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProductRepository;
import com.trainee.inv.repository.product.Product;

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
		Optional<GoodQuantityProduct> goodQuantityProduct = goodQuantityRepository.findById(goodQuantityProductId);
		goodQuantityProduct.get().setQuantity(quantity);
		return goodQuantityRepository.save(goodQuantityProduct.get());
	}

	@Override
	public List<GoodQuantityProduct> findByProductId(int id) {
		return goodQuantityRepository.findAllByProductId(id);
	}

	@Override
	public GoodQuantityProduct findById(int id) {
		return goodQuantityRepository.findById(id).get();
	}

	@Override

	public void deleteById(int id) {
		goodQuantityRepository.deleteById(id);
	}

	@Override
	public List<GoodQuantityProduct> findAll() {
		return goodQuantityRepository.findAll();
	}

}
