package com.trainee.inv.service.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
		if (existsByName) {
			throw new IllegalArgumentException("Warehouse Name Already Exist.");
		}
		return warehouseRepository.save(warehouse);
	}

	@Override
	public Warehouse update(Warehouse warehouse) {
//		boolean existsByName = warehouseRepository.existsByName(warehouse.getName());
//		boolean existsByAddress = warehouseRepository.existsByAddress(warehouse.getAddress());
//		boolean existsByDescription = warehouseRepository.existsByDescription(warehouse.getDescription());
		boolean existsById = warehouseRepository.existsById(warehouse.getId());
//		if (existsByName&existsByAddress&existsByDescription) {
//			throw new IllegalArgumentException("Warehouse Already added.");
//		} else
		if (!existsById) {
			throw new IllegalArgumentException("Id must be in database.");
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
	public void delete(int id) {
		warehouseRepository.deleteById(id);

	}

	@Override
	public Warehouse findById(int id) {
		Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
		Warehouse warehouse = optionalWarehouse.get();
		return warehouse != null ? warehouse : null;
	}

	@Override
	public List<Warehouse> searchByName(String name, boolean isActive) {
		List<Warehouse> list = findByIsActive(isActive);
		List<Warehouse> returnList = null;
		returnList = findingAllWarehouses(name, list, returnList);
		return returnList;
	}

	private List<Warehouse> findingAllWarehouses(String name, List<Warehouse> list, List<Warehouse> returnList) {
		for (Warehouse o : list) {
			if (o.getDescription().contains(name)) {
				if (returnList == null) {
					returnList = new ArrayList<Warehouse>();
				}
				returnList.add(o);
			}
		}
		return returnList;
	}

	@Override
	public List<Warehouse> searchByAddress(String address, boolean isActive) {
		List<Warehouse> list = findByIsActive(isActive);
		List<Warehouse> returnList = null;
		returnList = findingAllWarehouses(address, list, returnList);
		return returnList;
	}

	@Override
	public List<Warehouse> searchByDescription(String description, boolean isActive) {
		List<Warehouse> list = findByIsActive(isActive);
		List<Warehouse> returnList = null;
		returnList = findingAllWarehouses(description, list, returnList);
		return returnList;
	}

	@Override
	public List<Warehouse> findByIsActive(boolean isActive) {
		return warehouseRepository.findByIsActive(isActive);
		
	}




}
