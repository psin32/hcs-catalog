package co.uk.app.commerce.catalog.category.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Category> getAllCategories() {
		return categoryService.findAllCategories();
	}

	@GetMapping(path = "/topnav")
	public @ResponseBody Iterable<Category> getTopNav() {
		return categoryService.findTopNav();
	}

	@PostMapping(path = "/add")
	public ResponseEntity<?> persistCategory(@RequestBody Category category, HttpServletResponse response) {
		Category cat = categoryService.findCategoryByIdentifier(category.getIdentifier());
		if (null == cat) {
			category.setUrl(category.getIdentifier().replaceAll(" ", "-").toLowerCase());
			return ResponseEntity.ok(categoryService.persistCategory(category));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PostMapping(path = "/update")
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

	@GetMapping(path = "/{identifier}")
	public @ResponseBody Category getCategoryByIdentifier(@PathVariable("identifier") String identifier) {
		return categoryService.findCategoryByIdentifier(identifier);
	}

	@GetMapping(path = "/subcategories/{identifier}")
	public @ResponseBody Iterable<Category> getSubCategoriesByParentIdentifier(
			@PathVariable("identifier") String identifier) {
		return categoryService.findSubCategoriesByParentIdentifier(identifier);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
		categoryService.deleteCategoryById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping(path = "/url/{url}")
	public ResponseEntity<?> getCategoryByURL(@PathVariable("url") String url) {
		Category category = categoryService.findCategoryByURL(url);
		if(null == category) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(category);
	}
}
