package co.uk.app.commerce.catalog.catentry.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.catentry.service.CatentryService;

@RestController
@RequestMapping("/catentry")
public class CatentryController {

	@Autowired
	private CatentryService catentryService;

	@PostMapping(path = "/add")
	public ResponseEntity<?> persistCatentry(@RequestBody Catentry catentry, HttpServletResponse response) {
		Catentry cat = catentryService.findCatentryByPartnumber(catentry.getPartnumber());
		if (null == cat) {
			catentry.setUrl(catentry.getDescription().getName().replaceAll(" ", "-").toLowerCase());
			return ResponseEntity.ok(catentryService.persistCatentry(catentry));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PostMapping(path = "/update")
	public ResponseEntity<?> updateCatentry(@RequestBody Catentry catentry, HttpServletResponse response) {
		Catentry cat = catentryService.findCatentryByPartnumber(catentry.getPartnumber());
		if (null == cat) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catentry.setId(cat.getId());
		if (null != catentry.getPartnumber()) {
			catentry.setUrl(catentry.getDescription().getName().replaceAll(" ", "-").toLowerCase());
		}
		return ResponseEntity.ok(catentryService.updateCatentry(catentry));
	}

	@GetMapping(path = "/url/{url}")
	public ResponseEntity<?> getCatentryByURL(@PathVariable("url") String url) {
		Catentry catentry = catentryService.findCatentryByURL(url);
		if (null == catentry) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(catentry);
	}

	@GetMapping(path = "/{categoryidentifier}")
	public ResponseEntity<?> getCatentryByCategoryIdentifier(
			@PathVariable("categoryidentifier") String categoryidentifier) {
		return ResponseEntity.ok(catentryService.findCatentriesByCategoryIdentifier(categoryidentifier));
	}

}
