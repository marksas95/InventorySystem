package com.trainee.inv.resources.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.warehouse.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseResource {

	
	
	@Autowired
	private WarehouseService warehouseService;

	public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
		return warehouseService.create(warehouse) ;
	}
}
