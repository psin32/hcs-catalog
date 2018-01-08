package co.uk.app.commerce.catalog.catentry.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.catalog.category.service.CategoryService;
import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.catentry.repository.CatentryRepository;
import co.uk.app.commerce.catalog.common.bean.Association;
import co.uk.app.commerce.catalog.common.bean.AttributeDisplayType;
import co.uk.app.commerce.catalog.common.bean.Attributes;
import co.uk.app.commerce.catalog.common.bean.AttributesType;
import co.uk.app.commerce.catalog.common.bean.CatentryResponseBean;
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
	public CatentryResponseBean findProductData(String url) {
		CatentryResponseBean catentryResponseBean = null;
		Catentry catentry = findCatentryByURL(url);
		if (null != catentry) {
			CatentryType type = catentry.getType();
			if (type.equals(CatentryType.PRODUCTBEAN)) {
				catentryResponseBean = setProductResponse(catentry);
			} else if (type.equals(CatentryType.ITEMBEAN)) {

			}
		}
		return catentryResponseBean;
	}

	private CatentryResponseBean setProductResponse(Catentry catentry) {
		CatentryResponseBean catentryResponseBean = null;
		List<Association> childItems = catentry.getChilditems();

		String defaultItem = null;
		if (null != childItems && childItems.size() > 0) {
			defaultItem = catentry.getChilditems().get(0).getIdentifier();
			childItems.sort(new Comparator<Association>() {
				@Override
				public int compare(Association lhs, Association rhs) {
					return lhs.getSequence() < rhs.getSequence() ? -1 : (lhs.getSequence() > rhs.getSequence()) ? 1 : 0;
				}
			});
		}

		if (null != catentry.getAttributes() && catentry.getAttributes().size() > 0) {
			catentry.getAttributes().sort(new Comparator<Attributes>() {
				@Override
				public int compare(Attributes lhs, Attributes rhs) {
					return lhs.getSequence() < rhs.getSequence() ? -1 : (lhs.getSequence() > rhs.getSequence()) ? 1 : 0;
				}
			});
		}

		List<Catentry> skus = new ArrayList<>();
		childItems.stream().forEach(child -> {
			Catentry cat = catentryRepository.findByPartnumber(child.getIdentifier());
			skus.add(cat);
		});
		catentryResponseBean = new CatentryResponseBean();
		catentryResponseBean.setProduct(catentry);
		catentryResponseBean.setChild(skus);
		if (null != defaultItem) {
			catentryResponseBean.setDefaultItem(defaultItem);
		}
		return catentryResponseBean;
	}

	private Catentry getProduct() {
		return null;
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
		// updateAttributes(catentries);
		// updateColorAttribute(catentries);
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

	private void updateAttributes(List<Catentry> catentries) {
		catentries.stream().forEach(catentry -> {
			if (catentry.getType().equals(CatentryType.PRODUCTBEAN)) {
				List<Attributes> productAttributes = catentry.getAttributes();
				Map<String, String> map = new HashMap<>();
				List<Association> childitems = catentry.getChilditems();
				childitems.stream().forEach(childitem -> {
					String partnumber = childitem.getIdentifier();
					Catentry child = catentryRepository.findByPartnumber(partnumber);
					if (null != child) {
						List<Attributes> attributes = child.getAttributes();
						attributes.stream().forEach(attribute -> {
							if (attribute.getType().equals(AttributesType.DEFINING)) {
								map.put(attribute.getName(), attribute.getName());
							}
						});
					}
				});
				Double sequence = 1D;

				for (Map.Entry<String, String> entry : map.entrySet()) {
					Attributes attributes = new Attributes();
					attributes.setName(entry.getKey());
					attributes.setType(AttributesType.DEFINING);
					attributes.setDisplayType(AttributeDisplayType.GRID);
					attributes.setSequence(sequence++);

					productAttributes.add(attributes);
				}

				catentry.setAttributes(productAttributes);
				catentryRepository.save(catentry);
			}
		});
	}

	private void updateColorAttribute(List<Catentry> catentries) {
		catentries.stream().forEach(catentry -> {
			if (catentry.getType().equals(CatentryType.PRODUCTBEAN)) {
				List<Attributes> productAttributes = catentry.getAttributes();

				int index = 0;
				boolean isColorAttributeExist = false;
				for (Attributes attribute : productAttributes) {
					if (attribute.getType().equals(AttributesType.DEFINING)) {
						if (attribute.getName().equals("Color")) {
							attribute.setDisplayType(AttributeDisplayType.COLOR);
							attribute.setSequence(1.0D);

							productAttributes.set(index, attribute);
							isColorAttributeExist = true;
						}
					}
					index++;
				}

				index = 0;
				if (isColorAttributeExist) {
					for (Attributes attribute : productAttributes) {
						if (attribute.getType().equals(AttributesType.DEFINING)) {
							if (!attribute.getName().equals("Color")) {
								attribute.setDisplayType(AttributeDisplayType.GRID);
								Double sequence = attribute.getSequence();
								attribute.setSequence(sequence + 1);

								productAttributes.set(index, attribute);
								isColorAttributeExist = true;
							}
						}
						index++;
					}

				}

				catentry.setAttributes(productAttributes);
				catentryRepository.save(catentry);
			}
		});
	}

}
