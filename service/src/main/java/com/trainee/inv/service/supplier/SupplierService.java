package com.trainee.inv.service.supplier;

import java.util.List;

import com.trainee.inv.repository.supplier.Supplier;

public interface SupplierService {

	Supplier update(int id, String name);

	Supplier create(String name);

	Supplier findByName(String name);
	
	List<Supplier> findAll();
	
	void delete (int id);
	
}
