package co.uk.app.commerce.catalog.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.service.CategoryService;

@RestController
@RequestMapping("/api/topnav")
public class TopnavigationController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public @ResponseBody Iterable<Category> getTopNav() {
		return categoryService.findTopNav();
	}
}
