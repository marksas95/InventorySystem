package com.trainee.inv.repository.goodquantityproduct;

import java.util.List;

import com.trainee.inv.repository.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodQuantityProductRepository extends JpaRepository<GoodQuantityProduct, Integer> {
	
	List<GoodQuantityProduct> findAllByProductId(int id);
}
