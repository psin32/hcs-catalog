package co.uk.app.commerce.catalog.category.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.service.CategoryService;
import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.catentry.service.CatentryService;
import co.uk.app.commerce.catalog.common.bean.CategoryResponseBean;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CatentryService catentryService;

	@GetMapping
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryService.findAllCategories();
	}

	@PutMapping
	public ResponseEntity<?> persistCategory(@RequestBody Category category, HttpServletResponse response) {
		Category cat = categoryService.findCategoryByIdentifier(category.getIdentifier());
		if (null == cat) {
			category.setUrl(category.getIdentifier().replaceAll(" ", "-").toLowerCase());
			return ResponseEntity.ok(categoryService.persistCategory(category));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PatchMapping
	public ResponseEntity<?> updateCategory(@RequestBody Category category, HttpServletResponse response) {
		Category cat = categoryService.findCategoryByIdentifier(category.getIdentifier());
		if (null == cat) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		category.setId(cat.getId());
		if (null != category.getIdentifier()) {
			category.setUrl(category.getIdentifier().replaceAll(" ", "-").toLowerCase());
		}
		return ResponseEntity.ok(categoryService.updateCategory(category));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
		categoryService.deleteCategoryById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping(path = "/{url}")
	public ResponseEntity<?> getCategoryByURL(@PathVariable("url") String url) {
		Category category = categoryService.findCategoryByURL(url);
		if (null == category) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Collection<Catentry> catentries = catentryService.findCatentriesByCategoryUrl(url);
		Collection<Category> subcategories = categoryService
				.findSubCategoriesByParentIdentifier(category.getIdentifier());
		CategoryResponseBean categoryResponseBean = new CategoryResponseBean();
		categoryResponseBean.setCategory(category);
		categoryResponseBean.setSubcategories(subcategories);
		categoryResponseBean.setCatentries(catentries);
		return ResponseEntity.ok(categoryResponseBean);
	}
}
