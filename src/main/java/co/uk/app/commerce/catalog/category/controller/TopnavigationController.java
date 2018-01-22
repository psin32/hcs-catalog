package co.uk.app.commerce.catalog.category.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.service.CategoryService;

@RestController
@RequestMapping("/topnav")
public class TopnavigationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TopnavigationController.class);

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public @ResponseBody Iterable<Category> getTopNav() {
		LOGGER.info("Invoking get stop navigation");
		return categoryService.findTopNav();
	}
}
