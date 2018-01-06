package co.uk.app.commerce.catalog.catentry.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.catentry.service.CatentryService;

@RestController
@RequestMapping("/api/catentry")
public class CatentryController {

	@Autowired
	private CatentryService catentryService;

	@PutMapping
	public ResponseEntity<?> persistCatentry(@RequestBody Catentry catentry, HttpServletResponse response) {
		Catentry savedCatentry = catentryService.persistCatentry(catentry);
		if (null == savedCatentry) {
			return ResponseEntity.ok(savedCatentry);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PatchMapping
	public ResponseEntity<?> updateCatentry(@RequestBody Catentry catentry, HttpServletResponse response) {
		Catentry updatedCatentry = catentryService.updateCatentry(catentry);
		if (null == updatedCatentry) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok(updatedCatentry);
	}

	@GetMapping(path = "/{url}")
	public ResponseEntity<?> getCatentryByURL(@PathVariable("url") String url) {
		Catentry catentry = catentryService.findCatentryByURL(url);
		if (null == catentry) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(catentry);
	}

	@GetMapping
	public ResponseEntity<?> updateUrls() {
		catentryService.updateUrl();
		return ResponseEntity.ok().build();
	}

}
