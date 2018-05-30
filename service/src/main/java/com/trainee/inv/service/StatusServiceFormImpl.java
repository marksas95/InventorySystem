package com.trainee.inv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.product.ProductService;
import com.trainee.inv.service.warehouse.WarehouseService;

@Service
public class StatusServiceFormImpl implements StatusServiceForm {

	@Autowired
	ProductService productService;
	@Autowired
	WarehouseService warehouseService;
	
	@Override
	public Product setProductToActive(int productId) {
		 return setStatusOfProduct(productId,true);
	}

	@Override
	public Product setProductToInActive(int productId) {
		return setStatusOfProduct(productId,false);
	}

	@Override
	public Warehouse setWarehouseToActive(int warehouseId) {
		return setStatusOfWarehouse(warehouseId, true);
	}

	

	@Override
	public Warehouse setWarehouseToInActive(int warehouseId) {
		return setStatusOfWarehouse(warehouseId, false);
	}

	private Warehouse setStatusOfWarehouse(int warehouseId, boolean isActive) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		warehouse.setActive(isActive);
		return warehouseService.update(warehouse);
	}
	
	private Product setStatusOfProduct(int productId, boolean isActive) {
		Product product = productService.findById(productId);
		 product.setActive(isActive);
		 return productService.update(product);
	}
}
