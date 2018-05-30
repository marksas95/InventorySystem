package com.trainee.inv.repository.reconcileproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReconcileProductRepository extends JpaRepository<ReconcileProduct, Integer> {
	List<ReconcileProduct> findByGoodQuantityProductId(int id);
	List<ReconcileProduct> findByWarehouseId(int id);
	
	
}
