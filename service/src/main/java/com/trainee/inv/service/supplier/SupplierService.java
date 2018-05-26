package com.trainee.inv.service.supplier;

import java.util.List;

import com.trainee.inv.repository.supplier.Supplier;

public interface SupplierService {

	Supplier update(Supplier supplier, String name);

	Supplier create(String name);

	Supplier findByName(String name);
	
	List<Supplier> findAll();
	
	void delete (String name);
	
}
