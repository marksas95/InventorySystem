package com.trainee.inv.service.supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.repository.supplier.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public Supplier update(int id, String name) {
		boolean existsByName = supplierRepository.existsByName(name);
		if (existsByName) {
			throw new IllegalArgumentException("Supplier Name Already Exist");
		}
		Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
		Supplier supplier = optionalSupplier.get();
		supplier.setName(name);
		return supplierRepository.save(supplier);
	}

	@Override
	public Supplier create(String name) {
		boolean existsByName = supplierRepository.existsByName(name);
		if (existsByName) {
			throw new IllegalArgumentException("Supplier Name Already Exist");
		}
		Supplier supplier = new Supplier();
		supplier.setName(name);
		return supplierRepository.save(supplier);
	}

	@Override
	public Supplier findByName(String name) {
		Supplier supplier = supplierRepository.findByName(name);
		return supplier != null ? supplier : null;
	}

	@Override
	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	@Override
	public void delete(int id) {
		boolean existsById = supplierRepository.existsById(id);
		if (!existsById) {
			throw new IllegalArgumentException("cannot find id");
		}
		supplierRepository.deleteById(id);
	}

}
