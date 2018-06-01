package com.trainee.inv.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.StatusServiceForm;

@RestController
@RequestMapping("/api/status")
public class StatusResource {

	@Autowired
	StatusServiceForm statusServiceForm;

	@PostMapping("/setProductToActive")
	public Product setProductToActive(@RequestParam(name = "productId", required = true) int productId) {
		return statusServiceForm.setProductToActive(productId);
	}

	@PostMapping("/setProductToInActive")
	public Product setProductToInActive(@RequestParam(name = "productId", required = true) int productId) {
		return statusServiceForm.setProductToInActive(productId);
	}

	@PostMapping("/setWarehouseToActive")
	public Warehouse setWarehouseToActive(@RequestParam(name = "warehouseId", required = true) int warehouseId) {
		return statusServiceForm.setWarehouseToActive(warehouseId);
	}

	@PostMapping("/setWarehouseToInActive")
	public Warehouse setWarehouseToInActive(@RequestParam(name = "warehouseId", required = true) int warehouseId) {
		return statusServiceForm.setWarehouseToInActive(warehouseId);
	}
}
