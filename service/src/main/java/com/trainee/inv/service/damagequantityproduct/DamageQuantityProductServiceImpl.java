package com.trainee.inv.service.damagequantityproduct;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProductRepository;
import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;

@Service
public class DamageQuantityProductServiceImpl implements DamageQuantityProductService {

	@Autowired
	private DamageQuantityProductRepository damageQuantityRepository;

	@Override
	public DamageQuantityProduct create(Product product, int quantity) {
		DamageQuantityProduct damageQuantityProduct = new DamageQuantityProduct();
		damageQuantityProduct.setProduct(product);
		damageQuantityProduct.setQuantity(quantity);
		return damageQuantityRepository.save(damageQuantityProduct);
	}

	@Override
	public DamageQuantityProduct updateQuantity(int damageQuantityProductId, int quantity) {
		Optional<DamageQuantityProduct> damageQuantityProduct = damageQuantityRepository.findById(damageQuantityProductId);
		damageQuantityProduct.get().setQuantity(quantity);
		return damageQuantityRepository.save(damageQuantityProduct.get());
	}

	@Override
	public List<DamageQuantityProduct> findByProductId(int id) {
		return damageQuantityRepository.findByProductId(id);
	}

	@Override
	public DamageQuantityProduct findById(int id) {
		return damageQuantityRepository.findById(id).get();
	}


	@Override
	public List<DamageQuantityProduct> findAll() {
		return damageQuantityRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		damageQuantityRepository.deleteById(id);
		
	}

	


}
