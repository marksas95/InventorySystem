package com.trainee.inv.resources.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.warehouse.WarehouseService;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseResource {

	
	
	@Autowired
	private WarehouseService warehouseService;

	@GetMapping("/list")
	public List<Warehouse> findAll(){
		return warehouseService.findAll();
	}
	
	@PostMapping("/create")
	public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
		return warehouseService.create(warehouse) ;
	}
	
	@PostMapping("/update")
	public Warehouse updateWarehouse(@RequestBody Warehouse warehouse) {
		return warehouseService.update(warehouse);
	}
	
	@GetMapping("/findByName")
	public Warehouse findByName(@RequestParam(name = "name", required = true)String name) {
		return warehouseService.findByName(name);
	}
	
	@GetMapping("/findById")
	public Warehouse findByName(@RequestParam(name = "id", required = true) int id) {
		return warehouseService.findById(id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id",required = true) int id) {
		warehouseService.delete(id);
		
	}
	
	@GetMapping("/searchByName")
	public List<Warehouse> searchByName(@RequestParam(name = "name",required = true) String name,
										  @RequestParam(required = true) boolean isActive) {
		return warehouseService.searchByName(name, isActive);
	}
	
	@GetMapping("/searchByAddress")
	public List<Warehouse> searchByAddress(@RequestParam(name = "address",required = true) String address,
										  @RequestParam(required = true) boolean isActive) {
		return warehouseService.searchByAddress(address, isActive);
	}
	
	@GetMapping("/searchByDescription")
	public List<Warehouse> searchByDescription(@RequestParam(name = "description",required = true) String description,
										  @RequestParam(required = true) boolean isActive) {
		return warehouseService.searchByDescription(description, isActive);
	}
}
