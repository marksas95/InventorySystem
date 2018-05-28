package com.trainee.inv.service.warehouse;

import java.util.List;

import com.trainee.inv.repository.warehouse.Warehouse;



public interface WarehouseService {

	Warehouse create(String name);

	Warehouse update();

	Warehouse findByName(String name);

	List<Warehouse> findAll();

	void delete(String name);

}
