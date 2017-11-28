package co.uk.app.commerce.catalog.category.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping(path = "/add")
	public @ResponseBody Category persistCategory(@RequestBody Category category, HttpServletResponse response) {
		return categoryService.persistCategory(category);
	}
}
