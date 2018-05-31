package com.trainee.inv.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByIsActive(boolean isActive);

	List<Product> findByCategoryId(int id);

	List<Product> findBySupplierId(int id);

	Product findByName(String name);

	boolean existsByItemCode(String itemCode);

	boolean existsBySerialNumber(String serialNumber);
}
