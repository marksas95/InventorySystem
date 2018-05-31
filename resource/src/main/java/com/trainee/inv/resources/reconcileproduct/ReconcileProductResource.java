package com.trainee.inv.resources.reconcileproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.reconcileproduct.ReconcileProduct;
import com.trainee.inv.service.reconcileproduct.ReconcileProductService;

@RestController
@RequestMapping("/api/reconcileproduct")
public class ReconcileProductResource {

	@Autowired
	private ReconcileProductService ReconcileProductService;

	@GetMapping("/list")
	public List<ReconcileProduct> findAll() {
		return ReconcileProductService.findAll();
	}

	@PostMapping("/create")
	public ReconcileProduct createProduct(@RequestBody ReconcileProduct reconcileProduct) {
		return ReconcileProductService.create(reconcileProduct);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id", required = true) int id) {
		ReconcileProductService.delete(id);

	}

	@PostMapping("/update")
	public ReconcileProduct updateReconcileProduct(@RequestBody ReconcileProduct reconcileProduct) {
		return ReconcileProductService.update(reconcileProduct);
	}
	
	@GetMapping("/findById")
	public ReconcileProduct findById(@RequestParam(name = "id", required = true) int id) {
		return ReconcileProductService.findById(id);
	}
	
	@GetMapping("/findByGoodQuantityProductId")
	public List<ReconcileProduct> findByGoodQuantityProductId(@RequestParam(name = "id", required = true) int id) {
		return ReconcileProductService.findByGoodQuantityProductId(id);
	}
	
	@GetMapping("/findByWarehouseId")
	public List<ReconcileProduct> findByWarehouseId(@RequestParam(name = "id", required = true) int id) {
		return ReconcileProductService.findByWarehouseId(id);
	}
}
