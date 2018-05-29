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
	// @Ignore
	public void createTest() {

		String name = "blossbss";
		Category createdCategory = categoryService.create(name);
		System.out.println(createdCategory);
		Assert.notNull(createdCategory);
		Assert.isTrue(createdCategory.getName().equals(name));
	}

	@Test
	@Ignore
	public void createCategoryWithInvalidNameTest() {
		try {
			Category createdCategory = categoryService.create("blob");
			System.out.println(createdCategory);
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("Category Name Already Exists."));
		}
	}

	@Test
	@Ignore
	public void findByNameTest() {
		Category category = categoryService.findByName("blosssssb");
		Assert.notNull(category);
	}

	@Test
	@Ignore
	public void findByNameThatIsNotInDatabaseTest() {
		Category category = categoryService.findByName("weeee");
		Assert.isNull(category);
	}

	@Test
	@Ignore
	public void updateTest() {
		Category category = categoryService.findByName("admsin");
		Category updatedCategory = categoryService.update(category, "nullss");
		Assert.isTrue(updatedCategory.getName().equals("nullss"));
	}

	@Test
	@Ignore
	public void deleteTest() {
		categoryService.delete("nullss");
		Assert.isNull(categoryService.findByName("nulls"));
	}

	@Test
	@Ignore
	public void deleteThatHasInvalidNameTest() {
		try {
			categoryService.delete("blob");
		} catch (IllegalArgumentException e) {
			Assert.isTrue(e.getMessage().equals("No name in that category."));
		}

	}

	@Test
	@Ignore
	public void findAllTest() {
		List<Category> list = categoryService.findAll();
		Assert.notEmpty(list);
	}
}
