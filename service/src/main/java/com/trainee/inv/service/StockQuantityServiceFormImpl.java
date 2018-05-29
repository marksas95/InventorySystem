package com.trainee.inv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.repository.warehouse.Warehouse;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;
import com.trainee.inv.service.product.ProductService;
import com.trainee.inv.service.warehouse.WarehouseService;

@Service
public class StockQuantityServiceFormImpl implements StockQuantityServiceForm{

	@Autowired
	WarehouseService warehouseService;
	@Autowired
	ProductService productService;
	@Autowired
	GoodQuantityProductService goodQuantityProductService;
	
	
	@Override
	public void stockIn(int warehouseId, int goodQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for(GoodQuantityProduct o :goodQuantityProducts) {
			if(o.getId()==goodQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity += quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.update(o.getId(), initialQuantity);
				break;
			}
		}
	}

	@Override
	public void stockOut(int warehouseId, int goodQuantityProductId, int quantity) {
		Warehouse warehouse = warehouseService.findById(warehouseId);
		GoodQuantityProduct goodQuantityProduct = goodQuantityProductService.findById(goodQuantityProductId);
		List<GoodQuantityProduct> goodQuantityProducts = warehouse.getGoodQuantityProducts();
		for(GoodQuantityProduct o :goodQuantityProducts) {
			if(o.getId()==goodQuantityProductId) {
				int initialQuantity = o.getQuantity();
				initialQuantity -= quantity;
				GoodQuantityProduct newGoodQuantityProduct = goodQuantityProductService.update(o.getId(), initialQuantity);
				break;
			}
		}
	}

}
