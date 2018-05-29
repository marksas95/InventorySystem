package com.trainee.inv.service.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.category.CategoryRepository;
import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.repository.supplier.SupplierRepository;
@Service
public class SupplierServiceImpl implements SupplierService{
	@Autowired
	private SupplierRepository supplierRepository;
	private boolean nameExist;
	
	@Override
	public Supplier update(Supplier supplier, String name) {
		nameExist = checkIfSupplierNameExist(name);
		if(nameExist) {
			throw new IllegalArgumentException("Supplier Name Already Exist");
		}
		supplier.setName(name);
		return supplierRepository.save(supplier);
	}

	@Override
	public Supplier create(String name) {
		nameExist = checkIfSupplierNameExist(name);
		if(nameExist) {
			throw new IllegalArgumentException("Supplier Name Already Exist");
		}
		Supplier supplier = new Supplier();
		supplier.setName(name);
		return supplierRepository.save(supplier);
	}

	@Override
	public Supplier findByName(String name) {
		Supplier supplier = supplierRepository.findByName(name);
		return supplier!= null ? supplier : null;
	}

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public void delete(String name) {
		nameExist = checkIfSupplierNameExist(name);
		if(!nameExist) {
			throw new IllegalArgumentException("Supplier Name Already Exist");
		}
		Supplier supplier = supplierRepository.findByName(name);
		supplierRepository.delete(supplier);
		
	}
	
	
	private boolean checkIfSupplierNameExist(String name) {
		Supplier supplier = supplierRepository.findByName(name);
		return supplier != null;
	}

}
