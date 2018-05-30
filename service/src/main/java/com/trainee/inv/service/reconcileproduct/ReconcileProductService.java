package com.trainee.inv.service.reconcileproduct;

import java.util.List;

import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;

public interface ReconcileProductService {

	ReconcileProduct create(ReconcileProduct reconcileProduct);

	ReconcileProduct update(ReconcileProduct reconcileProduct);

	void delete(ReconcileProduct reconcileProduct);

	ReconcileProduct findById(int id);

	List<ReconcileProduct> findAll();

	List<ReconcileProduct> findByGoodQuantityProductId(int id);

	List<ReconcileProduct> findByWarehouseId(int id);
}
