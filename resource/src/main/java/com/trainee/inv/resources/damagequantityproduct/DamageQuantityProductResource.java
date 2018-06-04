package com.trainee.inv.resources.damagequantityproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.damagequantityproduct.DamageQuantityProduct;
import com.trainee.inv.service.damagequantityproduct.DamageQuantityProductService;

@RestController
@RequestMapping("/api/damagequantityproduct")
public class DamageQuantityProductResource {

	@Autowired
	private DamageQuantityProductService damageQuantityProductService;

	@PostMapping("/create")
	public DamageQuantityProduct createGoodQuantityProduct(
			@RequestBody CreateDamageQuantityProductForm createDamageQuantityProductForm) {

		return damageQuantityProductService.create(createDamageQuantityProductForm.getProduct(),
				createDamageQuantityProductForm.getQuantity());
	}

	@PostMapping("/updateQuantity")
	public DamageQuantityProduct updateGoodQuantityProduct(
			@RequestParam(name = "id", required = true) Integer goodQuantityProductId,
			@RequestParam(required = true) Integer quantity) {

		return damageQuantityProductService.updateQuantity(goodQuantityProductId, quantity);
	}

	@GetMapping("/findByProductId")
	public List<DamageQuantityProduct> findByName(@RequestParam(name = "id", required = true) int id) {
		return damageQuantityProductService.findByProductId(id);
	}

	@GetMapping("/findById")
	public DamageQuantityProduct findById(@RequestParam(name = "id", required = true) int id) {
		return damageQuantityProductService.findById(id);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id", required = true) int id) {
		damageQuantityProductService.delete(id);

	}

	@GetMapping("/list")
	public List<DamageQuantityProduct> findAll() {
		return damageQuantityProductService.findAll();
	}
}
