package com.trainee.inv.service.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.repository.warehouse.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;

	@Override
	public Warehouse create(Warehouse warehouse) {
		checkIfNameExists(warehouse);
		return warehouseRepository.save(warehouse);
	}

	private void checkIfNameExists(Warehouse warehouse) {
		if (warehouseRepository.existsByName(warehouse.getName())) {
			throw new IllegalArgumentException("Warehouse Name Already Exist.");
		}
	}

	@Override
	//to be edit
	public Warehouse update(Warehouse warehouse) {
		if (warehouseRepository.existsByName(warehouse.getName())) {
			if (warehouseRepository.findByName(warehouse.getName()).getId() != warehouse.getId()) {
				throw new IllegalArgumentException("Warehouse Name Already Exist.");
			}
		}
		return warehouseRepository.save(warehouse);
	}

	@Override
	public Warehouse findByName(String name) {
		return warehouseRepository.findByName(name);
	}

	@Override
	public List<Warehouse> findAll() {
		return warehouseRepository.findAll();
	}

	@Override
	public void delete(int id) {
		warehouseRepository.deleteById(id);

	}

	@Override
	public Warehouse findById(int id) {
		return warehouseRepository.findById(id).get();

	}

	@Override
	public List<Warehouse> searchByName(String name, boolean isActive) {
		List<Warehouse> warehouses = findByIsActive(isActive);
		return findingAllWarehousesByName(name, warehouses);

	}

	private List<Warehouse> findingAllWarehousesByName(String name, List<Warehouse> warehouses) {
		return warehouses.stream().filter(o -> o.getName().contains(name)).collect(Collectors.toList());
	}

	@Override
	public List<Warehouse> searchByAddress(String address, boolean isActive) {
		List<Warehouse> list = findByIsActive(isActive);
		return findingAllWarehousesByAddress(address, list);
	}

	private List<Warehouse> findingAllWarehousesByAddress(String address, List<Warehouse> warehouses) {
		return warehouses.stream().filter(o -> o.getAddress().contains(address)).collect(Collectors.toList());
	}

	@Override
	public List<Warehouse> searchByDescription(String description, boolean isActive) {
		List<Warehouse> warehouses = findByIsActive(isActive);
		return findingAllWarehousesByDescription(description, warehouses);
	}

	private List<Warehouse> findingAllWarehousesByDescription(String description, List<Warehouse> warehouses) {
		return warehouses.stream().filter(o -> o.getAddress().contains(description)).collect(Collectors.toList());
	}

	@Override
	public List<Warehouse> findByIsActive(boolean isActive) {
		return warehouseRepository.findByIsActive(isActive);
		
	}




}
