package com.trainee.inv.resources.supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Supplier updateSupplier(@RequestBody UpdateSupplierForm updateSupplierForm){
		return supplierService.update(updateSupplierForm.getId(),updateSupplierForm.getName());
	}
	
	@GetMapping("/findByName")
	public Supplier findByname(@RequestParam(name = "name", required = true)String name) {
		return supplierService.findByName(name);
	}
	
}
