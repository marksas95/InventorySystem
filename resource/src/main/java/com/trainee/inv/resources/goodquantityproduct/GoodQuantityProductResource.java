package com.trainee.inv.resources.goodquantityproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.goodquantityproduct.GoodQuantityProduct;
import com.trainee.inv.service.goodquantityproduct.GoodQuantityProductService;

@RestController
@RequestMapping("/api/goodquantityproduct")
public class GoodQuantityProductResource {
	
	@Autowired
	private GoodQuantityProductService goodQuantityProductService;

	
	@PostMapping("/create")
	public GoodQuantityProduct createGoodQuantityProduct(@RequestBody 
											CreateGoodQuantityProductForm createGoodQuantityProductForm) {
		
		return goodQuantityProductService.create(createGoodQuantityProductForm.getProduct(),
												 createGoodQuantityProductForm.getQuantity());
	}
	
	@PostMapping("/updateQuantity")
	public GoodQuantityProduct updateGoodQuantityProduct(@RequestParam(name = "id",required = true) 
																Integer goodQuantityProductId,
			   											@RequestParam(required = true) Integer quantity) {
		
		return goodQuantityProductService.updateQuantity(goodQuantityProductId, quantity);
	}
	
	@GetMapping("/findByProductId")
	public List<GoodQuantityProduct> findByName(@RequestParam(name = "id", required = true)int id) {
		return goodQuantityProductService.findByProductId(id);
	}
	
	@GetMapping("/findById")
	public GoodQuantityProduct findById(@RequestParam(name = "id", required = true) int id) {
		return goodQuantityProductService.findById(id);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id",required = true) int id) {
		goodQuantityProductService.delete(id);
		
	}
	
	@GetMapping("/list")
	public List<GoodQuantityProduct> findAll(){
		return goodQuantityProductService.findAll();
	}
}
