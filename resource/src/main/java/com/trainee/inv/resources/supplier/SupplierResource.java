package com.trainee.inv.resources.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.supplier.Supplier;
import com.trainee.inv.resources.category.UpdateCategoryForm;
import com.trainee.inv.service.supplier.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierResource {
	
	@Autowired
	private SupplierService supplierService;

	@GetMapping("/list")
	public List<Supplier> findAllSuppliers(){
		return supplierService.findAll();
	}
	
	@PostMapping("/create")
	public Supplier createSupplier(@RequestParam(name = "name",required = true) String name){
		return supplierService.create(name);
	}
	
	@PostMapping("/update")
	public Supplier updateSupplier(@RequestParam(name = "id",required = true) Integer id,
			   @RequestParam(required = true) String name){
		return supplierService.update(id,name);
	}
	
	@GetMapping("/findByName")
	public Supplier findByName(@RequestParam(name = "name", required = true)String name) {
		return supplierService.findByName(name);
	}
	
	@GetMapping("/findById")
	public Supplier findById(@RequestParam(name = "id", required = true) int id) {
		return supplierService.findById(id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id",required = true) int id) {
		supplierService.delete(id);
		
	}
	
}
