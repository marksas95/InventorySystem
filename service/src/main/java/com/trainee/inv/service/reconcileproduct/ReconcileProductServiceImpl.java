package com.trainee.inv.service.reconcileproduct;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.repository.reconcileproduct.ReconcileProductRepository;

@Service
public class ReconcileProductServiceImpl implements ReconcileProductService {

	@Autowired
	ReconcileProductRepository reconcileProductRepository;

	@Override
	public ReconcileProduct create(ReconcileProduct reconcileProduct) {
		return reconcileProductRepository.save(reconcileProduct);
	}

	@Override
	public ReconcileProduct update(ReconcileProduct reconcileProduct) {
		return reconcileProductRepository.save(reconcileProduct);
	}

	@Override
	public void deleteById(int id) {
		reconcileProductRepository.deleteById(id);
	}

	@Override
	public ReconcileProduct findById(int id) {
		Optional<ReconcileProduct> optional = reconcileProductRepository.findById(id);
		return optional.get();
	}

	@Override
	public List<ReconcileProduct> findAll() {
		return reconcileProductRepository.findAll();
	}

	@Override
	public List<ReconcileProduct> findByGoodQuantityProductId(int id) {
		return reconcileProductRepository.findByGoodQuantityProductId(id);
	}

	@Override
	public List<ReconcileProduct> findByWarehouseId(int id) {
		return reconcileProductRepository.findByWarehouseId(id);
	}

}
