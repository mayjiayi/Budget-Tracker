package com.fdmgroup.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.repository.CategoryRepository;
/**
 * CategoryService is responsible for all methods related to manipulating the category entity in the database.
 * 
 * @author May Lwin
 */
@Service
public class CategoryService {
	
	// attributes 
	@Autowired
	private CategoryRepository categoryRepo;
	private static final Logger logger = LoggerFactory.getLogger(Category.class);
	
	// behaviors
	
	/**
	 * Creates category entities to be saved into the database
	 * 
	 * @param categoryName
	 * @return true if category has been created, false if category already exists in database
	 */
	public boolean createCategory(String categoryName) {
		List<Category> categoryList = categoryRepo.findByCategoryName(categoryName);
		if(categoryList.isEmpty()) {
			Category newCategory = new Category(categoryName);
			categoryRepo.save(newCategory);
			return true;
		} else {
			logger.info("Category '" + categoryName + "' already exists in database.");
			return false;
		}
	}
	
	/**
	 * Retrieves all categories saved in the database
	 * 
	 * @return a list of categories that have been saved in the database
	 */
	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryRepo.findAll();
		
		if (categoryList.isEmpty() || categoryList == null) {
			logger.warn("No categories found in the database.");
		}
		return categoryList;
	}
	
	/**
	 * Retrieves category saved in the database by using categoryIds
	 * 
	 * @param id
	 * @return category of the argument id in the database
	 */
	public Category findById(int id) {
		Optional<Category> category = categoryRepo.findById(id);
		
		if (category.isPresent()) {
			return category.get();
		} else {
			logger.warn("Category with ID " + id + " not found.");
			return null;
		}
	}
}
