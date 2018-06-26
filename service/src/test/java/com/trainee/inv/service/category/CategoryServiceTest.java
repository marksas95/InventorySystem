package com.trainee.inv.service.category;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.trainee.inv.repository.category.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	 @Ignore
	public void createTest() {

		String name = "Food";
		Category createdCategory = categoryService.create(name);
		System.out.println(createdCategory);
		Assert.notNull(createdCategory);
		Assert.isTrue(createdCategory.getName().equals(name));
	}

	@Test
	@Ignore
	public void createCategoryWithInvalidNameTest() {
		try {
			String name = "Food";
			Category createdCategory = categoryService.create(name);
			System.out.println(createdCategory);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Category Name Already Exists."));
		}
	}

	@Test
	@Ignore
	public void findByNameTest() {
		String name = "Food";
		Category category = categoryService.findByName(name);
		Assert.isTrue(name.equals(category.getName()));
	}

	
	
	@Test
	@Ignore
	public void findByNameThatIsNotInDatabaseTest() {
		String name = "weeee";
		Category category = categoryService.findByName(name);
		Assert.isNull(category);
	}

	@Test
	@Ignore
	public void updateTest() {
		String name = "Shirt";
		int id = 5;
		
		Category oldCategory = categoryService.findById(id);
		Category updatedCategory = categoryService.update(id, name);
		Assert.isTrue(updatedCategory.getName().equals(name));
	}

	@Test
	@Ignore
	public void deleteTest() {
		int id = 1;
		categoryService.delete(id);
		Assert.isNull(categoryService.findById(id));
	}

	@Test
	@Ignore
	public void deleteThatHasInvalidNameTest() {
		try {
			int id = 100;
			categoryService.delete(id);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("No id in that category."));
		}

	}

	@Test
	@Ignore
	public void findAllTest() {
		List<Category> list = categoryService.findAll();
		Assert.notEmpty(list);
		System.out.println(list);
	}
}
