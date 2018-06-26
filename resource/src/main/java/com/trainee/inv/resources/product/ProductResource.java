package com.trainee.inv.resources.product;

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
import com.trainee.inv.service.product.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public List<Product> findAll() {
		return productService.findAll();
	}

	@PostMapping("/create")
	public Product createProduct(@RequestBody Product product) {
		return productService.create(product);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id", required = true) int id) {
		productService.delete(id);

	}

	@PostMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return productService.update(product);
	}

	@GetMapping("/findById")
	public Product findById(@RequestParam(name = "id", required = true) int id) {
		return productService.findById(id);
	}

	@GetMapping("/findByCategoryId")
	public List<Product> findByCategoryId(@RequestParam(name = "id", required = true) int id) {
		return productService.findByCategoryId(id);
	}

	@GetMapping("/findBySupplierId")
	public List<Product> findBySupplierId(@RequestParam(name = "id", required = true) int id) {
		return productService.findBySupplierId(id);
	}

	@GetMapping("/findByName")
	public Product findByName(@RequestParam(name = "name") String name) {
		return productService.findByName(name);
	}

	@GetMapping("/findByIsActive")
	public List<Product> findByisActive(@RequestParam(name = "isActive") boolean isActive) {
		return productService.findByIsActive(isActive);
	}

	@GetMapping("/searchByItemCode")
	public List<Product> searchByItemCode(@RequestParam(name = "itemCode") String itemCode) {
		return productService.searchByItemCode(itemCode);
	}

	@GetMapping("/searchByDescription")
	public List<Product> searchByDescription(@RequestParam(name = "description") String description) {
		return productService.searchByDescription(description);
	}

	@GetMapping("/searchByUnitOfMeasurement")
	public List<Product> unitOfMeasurement(
			@RequestParam(name = "unitOfMeasurement") String unitOfMeasurement) {
		return productService.searchByUnitOfMeasurement(unitOfMeasurement);
	}

	
}
