package co.uk.app.commerce.catalog.category.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.repository.CategoryRepository;
import co.uk.app.commerce.catalog.common.bean.Association;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Collection<Category> findAllCategories() {
		Collection<Category> categories = categoryRepository.findAll();

		List<Category> categoryToBeSaved = new ArrayList<>();

		categories.stream().forEach(category -> {
			if (null != category.getDescription() && null != category.getDescription().getName()) {
				category.setUrl(category.getDescription().getName().replaceAll(" ", "-").toLowerCase());
			}

			List<Association> subcategories = category.getChildcategories();
			if (null != subcategories) {
				subcategories.stream().forEach(subcategory -> {
					if (null != subcategory.getIdentifier()) {
						Category child = categoryRepository.findByIdentifier(subcategory.getIdentifier());
						subcategory.setName(child.getDescription().getName());
						subcategory.setUrl(child.getUrl());
					}
				});
			}
			categoryToBeSaved.add(category);
			// categoryRepository.save(category);
		});

		categoryRepository.save(categoryToBeSaved);

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

	@Override
	public Category findCategoryByURL(String url) {
		return categoryRepository.findByUrl(url);
	}

	@Override
	public List<Category> findSubCategoriesByParentIdentifier(String identifier) {
		return categoryRepository.findAllSubcategories(identifier);
	}

	@Override
	public Collection<Category> findTopNav() {
		return categoryRepository.findByTopnavIsTrue();
	}

	@Override
	public Category findCategoryById(String id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Category findCategoryByProductIdentifier(String partnumber) {
		return categoryRepository.findCategoryByProductIdentifier(partnumber);
	}

	@Override
	public Collection<Category> findTopCategories() {
		return categoryRepository.findAll();
	}

}
