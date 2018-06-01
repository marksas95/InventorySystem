package com.trainee.inv.service.warehouse;

import java.util.List;

import com.trainee.inv.repository.warehouse.Warehouse;

public interface WarehouseService {

	Warehouse create(Warehouse warehouse);

	Warehouse update(Warehouse warehouse);

	Warehouse findByName(String name);
	
	List<Warehouse> findByIsActive(boolean isActive);

	Warehouse findById(int id);

	List<Warehouse> findAll();

	void delete(int id);

	List<Warehouse> searchByName(String name, boolean isActive);

	List<Warehouse> searchByAddress(String address, boolean isActive);
	
	List<Warehouse> searchByDescription(String description, boolean isActive);

}
