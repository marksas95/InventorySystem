package com.trainee.inv.service.warehouse;

import java.util.List;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;



public interface WarehouseService {

	Warehouse create(Warehouse warehouse);

	Warehouse update(Warehouse warehouse);

	Warehouse findByName(String name);
	
	Warehouse findById(int id);

	List<Warehouse> findAll();
	
	void delete(Warehouse warehouse);

}
