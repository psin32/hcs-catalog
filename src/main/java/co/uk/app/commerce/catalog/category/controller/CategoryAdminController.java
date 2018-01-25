package co.uk.app.commerce.catalog.category.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.category.service.CategoryService;
import co.uk.app.commerce.catalog.constant.CatalogConstants;

@RestController
@RequestMapping("/category/admin")
public class CategoryAdminController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "/top")
	public ResponseEntity<?> getAllTopCategories(HttpServletRequest request) {
		String role = String.valueOf(request.getAttribute(CatalogConstants.REQUEST_HEADER_ROLE));
		if (null == role || (null != role && !role.equalsIgnoreCase("ADMIN"))) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		return ResponseEntity.ok(categoryService.findTopCategories());
	}
}
