package co.uk.app.commerce.catalog.category.service;

import java.util.Collection;

import co.uk.app.commerce.catalog.category.document.Category;

public interface CategoryService {

	Collection<Category> findAllCategories();
	
	Collection<Category> findTopNav();

	Collection<Category> findSubCategoriesByParentIdentifier(String id);

	Category persistCategory(Category category);

	Category updateCategory(Category category);

	Category findCategoryByIdentifier(String identifier);

	Category findCategoryByURL(String url);

	void deleteCategoryById(String id);
	
	Category findCategoryById(String id);
	
	Category findCategoryByProductIdentifier(String partnumber);
}
