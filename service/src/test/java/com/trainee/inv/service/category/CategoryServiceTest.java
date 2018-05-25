package com.trainee.inv.service.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.service.category.CategoryService;
import com.trainee.inv.service.category.CategoryServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService categoryService;

	@Test
	public void test() {
		
		Category createdCategory = categoryService.create("blossb");
		System.out.println(createdCategory);
	}
}
