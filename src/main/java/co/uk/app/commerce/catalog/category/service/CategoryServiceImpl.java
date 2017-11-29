package co.uk.app.commerce.catalog.category.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.repository.CategoryRepository;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Collection<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category persistCategory(Category category) {
		return categoryRepository.insert(category);
	}

	@Override
	public Category findCategoryByIdentifier(String identifier) {
		return categoryRepository.findByIdentifier(identifier);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategoryById(String id) {
		categoryRepository.delete(id);
	}

}
