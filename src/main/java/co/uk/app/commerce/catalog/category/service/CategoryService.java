package co.uk.app.commerce.catalog.category.service;

import java.util.Collection;

import co.uk.app.commerce.catalog.category.document.Category;

public interface CategoryService {

	Collection<Category> findAllCategories();
	
	Category persistCategory(Category category);
	
	Category findCategoryByIdentifier(String identifier);
}
