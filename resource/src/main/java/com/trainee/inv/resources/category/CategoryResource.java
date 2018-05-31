package com.trainee.inv.resources.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.service.category.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public List<Category> findAllCategories(){
		return categoryService.findAll();
	}
	
	@PostMapping("/create")
	public Category createCategory(@RequestParam(name = "name",required = true) String name){
		return categoryService.create(name);
	}
	
	@GetMapping("/{name}")
	public Category findByName1(@PathVariable(name = "name") String name){
		return categoryService.findByName(name);
	}
	
	@GetMapping("/findByName")
	public Category findByName2(@RequestParam(name = "name",required = true) String name){
		return categoryService.findByName(name);
	}
	
	@PostMapping("/update")
	public Category updateCategory(@RequestParam(name = "id",required = true) Integer id,
								   @RequestParam(required = true) String name){
		return categoryService.update(id, name);
	}
	
//	@PostMapping("/update")
//	public Category updateCategory(@RequestBody UpdateCategoryForm updateCategoryForm){
//		return categoryService.update(updateCategoryForm.getId(), updateCategoryForm.getName());
//	}
//	
//
//	@PostMapping("/update")
//	public Category updateCategory(@RequestBody UpdateCategoryForm updateCategoryForm){
//		return categoryService.update(updateCategoryForm.getCategory(), updateCategoryForm.getName());
//	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam(value = "id",required = true) int id) {
		categoryService.delete(id);
		
	}
}
