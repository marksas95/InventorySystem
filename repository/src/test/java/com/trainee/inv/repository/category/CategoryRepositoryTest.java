package com.trainee.inv.repository.category;



import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trainee.inv.repository.category.Category;
import com.trainee.inv.repository.category.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	@Ignore
	public void createCategoryTest() {
		Category category = new Category();
		category.setName("Toy");
		Category categorySave = categoryRepository.save(category);
		System.out.println(categorySave);
	}
	@Test
//	@Ignore
	public void findByNameTest() {
		Category findByNameCategory = categoryRepository.findByName("Food");
		System.out.println(findByNameCategory);
	}
	
}
