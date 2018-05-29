package com.trainee.inv.service.warehouse;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.repository.warehouse.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Override
	public Warehouse create(Warehouse warehouse) {
		boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
		if (existsByName) {
			throw new IllegalArgumentException("Warehouse Name Already Exist.");
		}
		return warehouseRepository.save(warehouse);
	}

	@Override
	public Warehouse update(Warehouse warehouse) {
		boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
		boolean existsByAddress = warehouseRepository.existsByAddress(warehouse.getAddress());
		boolean existsByDescription = warehouseRepository.existsByDescription(warehouse.getDescription());
		if (existsByName&&existsByAddress&&existsByDescription) {
			throw new IllegalArgumentException("Warehouse Name Already added.");
		}
		return warehouseRepository.save(warehouse);
	}

	@Override
	public Warehouse findByName(String name) {
		Warehouse warehouse = warehouseRepository.findByName(name);
		return warehouse != null ? warehouse : null;
	}

	@Override
	public List<Warehouse> findAll() {
		return warehouseRepository.findAll();
	}

	@Override
	public void delete(Warehouse warehouse) {
		warehouseRepository.delete(warehouse);

	}

	@Override
	public Warehouse findById(int id) {
		Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
		Warehouse warehouse = optionalWarehouse.get();
		return warehouse != null ? warehouse : null;
	}


}
