package com.trainee.inv.repository.goodquantityproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodQuantityProductRepository extends JpaRepository<GoodQuantityProduct, Integer> {
	
	List<GoodQuantityProduct> findByProductId(int id);
}
