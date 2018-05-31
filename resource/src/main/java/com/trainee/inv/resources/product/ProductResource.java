package com.trainee.inv.resources.product;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.product.Product;
import com.trainee.inv.service.product.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@GetMapping("/findAll")
	public List <Product> findAll() {
		return productService.findAll();
		
	}
	
}
