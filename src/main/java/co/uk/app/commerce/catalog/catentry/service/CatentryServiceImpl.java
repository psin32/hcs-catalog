package co.uk.app.commerce.catalog.catentry.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.service.CategoryService;
import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.catentry.repository.CatentryRepository;
import co.uk.app.commerce.catalog.common.bean.Association;
import co.uk.app.commerce.catalog.common.bean.CatentryType;

@Component
public class CatentryServiceImpl implements CatentryService {

	@Autowired
	private CatentryRepository catentryRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	public Collection<Catentry> findCatentriesByCategoryIdentifier(String identifier) {
		return catentryRepository.findByCategoryIdentifier(identifier);
	}

	@Override
	public Catentry findCatentryByURL(String url) {
		return catentryRepository.findByUrl(url);
	}

	@Override
	public void deleteCatentryById(String id) {
		catentryRepository.delete(id);
	}

	@Override
	public Catentry updateCatentry(Catentry catentry) {
		Catentry updatedCatentry = null;
		Catentry cat = findCatentryByPartnumber(catentry.getPartnumber());
		if (null == cat) {
			return null;
		}
		catentry.setId(cat.getId());
		if (null != catentry.getPartnumber()) {
			String url = catentry.getDescription().getName().replaceAll(" ", "-").toLowerCase();
			Catentry byUrlCategory = findCatentryByURL(url);
			if (null != byUrlCategory && !catentry.getPartnumber().equalsIgnoreCase(byUrlCategory.getPartnumber())) {
				url = url + "-" + catentry.getPartnumber().toLowerCase();
			}
			catentry.setUrl(url);

			List<Association> categories = getAssociationList(catentry);
			// catentry.setCategories(categories);

			updatedCatentry = catentryRepository.save(catentry);
		}
		return updatedCatentry;
	}

	private List<Association> getAssociationList(Catentry catentry) {
		List<Association> categories = new ArrayList<>();

		// catentry.getCategories().stream().forEach(association -> {
		//
		// String categoryIdentifier = association.getIdentifier();
		// Category category =
		// categoryService.findCategoryByIdentifier(categoryIdentifier);
		//
		// if (null != category) {
		// Association updatedAssociation = new Association();
		// updatedAssociation.setIdentifier(association.getIdentifier());
		// updatedAssociation.setName(category.getDescription().getName());
		// updatedAssociation.setUrl(category.getUrl());
		// updatedAssociation.setType(category.getClass().getName());
		// categories.add(updatedAssociation);
		// }
		// });
		return categories;
	}

	@Override
	public Catentry persistCatentry(Catentry catentry) {
		Catentry savedCatentry = null;
		Catentry cat = findCatentryByPartnumber(catentry.getPartnumber());
		if (null == cat) {
			String url = catentry.getDescription().getName().replaceAll(" ", "-").toLowerCase();
			Catentry byUrlCategory = findCatentryByURL(url);
			if (null != byUrlCategory && !catentry.getPartnumber().equalsIgnoreCase(byUrlCategory.getPartnumber())) {
				url = url + "-" + catentry.getPartnumber().toLowerCase();
			}
			catentry.setUrl(url);
			List<Association> categories = getAssociationList(catentry);
			// catentry.setCategories(categories);

			savedCatentry = catentryRepository.insert(catentry);
		}
		return savedCatentry;
	}

	@Override
	public Catentry findCatentryByPartnumber(String partnumber) {
		return catentryRepository.findByPartnumber(partnumber);
	}

	@Override
	public Collection<Catentry> findAllCatentriesForCategory(List<Association> products) {
		List<String> partnumber = new ArrayList<>();
		if (null != products) {
			products.stream().forEach(product -> {
				partnumber.add(product.getIdentifier());
			});
		}
		return catentryRepository.findByCatentryPartnumber(partnumber, CatentryType.PRODUCTBEAN.toString());
	}

	@Override
	public void updateUrl() {
		List<Catentry> catentries = catentryRepository.findAll();
		setUrlNull(catentries);
		setUrl(catentries, CatentryType.PRODUCTBEAN);
		setUrl(catentries, CatentryType.ITEMBEAN);
	}

	private void setUrl(List<Catentry> catentries, CatentryType type) {
		catentries.stream().forEach(catentry -> {
			if (catentry.getType().equals(type)) {
				String url = catentry.getDescription().getName().replaceAll(" ", "-").toLowerCase();
				Catentry byUrlCategory = findCatentryByURL(url);
				if (null != byUrlCategory
						&& !catentry.getPartnumber().equalsIgnoreCase(byUrlCategory.getPartnumber())) {
					url = url + "__" + catentry.getPartnumber().toLowerCase();
				}
				catentry.setUrl(url);
				catentryRepository.save(catentry);
			}
		});
	}

	private void setUrlNull(List<Catentry> catentries) {
		catentries.stream().forEach(catentry -> {
			catentry.setUrl(null);
			catentryRepository.save(catentry);
		});
	}

}
