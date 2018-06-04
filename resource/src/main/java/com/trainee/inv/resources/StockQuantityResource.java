package com.trainee.inv.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.service.StockQuantityService;

@RestController
@RequestMapping("/api/stockquantity")
public class StockQuantityResource {

	@Autowired
	StockQuantityService stockQuantityService;

	@PostMapping("/stockInGoodQuantityProduct")
	public void stockInGoodQuantityProduct(@RequestParam(name = "warehouseId", required = true) int warehouseId,
			@RequestParam(name = "productId", required = true) int goodQuantityProductId,
			@RequestParam(name = "quantity", required = true) int quantity) {
		stockQuantityService.stockInGoodQuantityProduct(warehouseId, goodQuantityProductId, quantity);
	}

	@PostMapping("/stockOutGoodQuantityProduct")
	public void stockOutGoodQuantityProduct(@RequestParam(name = "warehouseId", required = true) int warehouseId,
			@RequestParam(name = "productId", required = true) int goodQuantityProductId,
			@RequestParam(name = "quantity", required = true) int quantity) {
		stockQuantityService.stockOutGoodQuantityProduct(warehouseId, goodQuantityProductId, quantity);
	}

	@PostMapping("/stockInDamageQuantityProduct")
	public void stockInDamageQuantityProduct(@RequestParam(name = "warehouseIdFrom", required = true) int warehouseIdFrom,
			@RequestParam(name = "warehouseIdTo", required = true) int warehouseIdTo,
			@RequestParam(name = "productId", required = true) int productId,
			@RequestParam(name = "quantity", required = true) int quantity) {
		stockQuantityService.stockInDamageQuantityProduct(warehouseIdFrom, warehouseIdTo, productId, quantity);;
	}

	@PostMapping("/stockOutDamageQuantityProduct")
	public void stockOutDamageQuantityProduct(@RequestParam(name = "warehouseId", required = true) int warehouseId,
			@RequestParam(name = "damageQuantityProductId", required = true) int damageQuantityProductId,
			@RequestParam(name = "quantity", required = true) int quantity) {
		stockQuantityService.stockOutDamageQuantityProduct(warehouseId, damageQuantityProductId, quantity);
	}
	
	@PostMapping("/transferStocks")
	public void transferStocks(@RequestParam(name = "warehouseIdFrom", required = true) int warehouseIdFrom,
			@RequestParam(name = "warehouseIdTo", required = true) int warehouseIdTo,
			@RequestParam(name = "goodQuantityProductIdFrom", required = true) int productId,
			@RequestParam(name = "quantity", required = true) int quantity) {
		stockQuantityService.transferStocks(warehouseIdFrom, warehouseIdTo, productId, quantity);;
	}
	
	@PostMapping("/reconcileProduct")
	public void reconcileProduct(@RequestParam(name = "warehouseId", required = true) int warehouseId,
			@RequestParam(name = "productId", required = true) int productId,
			@RequestParam(name = "quantity", required = true) int physicalQuantity) {
		stockQuantityService.reconcileProduct(warehouseId, productId, physicalQuantity);
	}
	
	@GetMapping("/getAllThatReachedMinimumStocks")
	public List<GoodQuantityProduct> findByGoodQuantityProductThatReachedMinimumStocks(){
		return stockQuantityService.findByGoodQuantityProductThatReachedMinimumStocks();
	}
}
