package co.uk.app.commerce.catalog.common.bean;

import java.util.Collection;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.catentry.document.Catentry;

public class CategoryResponseBean {

	private Category category;
	
	private Collection<Category> subcategories;
	
	private Collection<Catentry> catentries;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Collection<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Collection<Category> subcategories) {
		this.subcategories = subcategories;
	}

	public Collection<Catentry> getCatentries() {
		return catentries;
	}

	public void setCatentries(Collection<Catentry> catentries) {
		this.catentries = catentries;
	}
}
