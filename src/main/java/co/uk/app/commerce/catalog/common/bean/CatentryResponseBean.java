package co.uk.app.commerce.catalog.common.bean;

import java.util.List;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.catentry.document.Catentry;

public class CatentryResponseBean {

	private Catentry product;

	private List<Catentry> child;

	private String defaultItem;

	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDefaultItem() {
		return defaultItem;
	}

	public void setDefaultItem(String defaultItem) {
		this.defaultItem = defaultItem;
	}

	public Catentry getProduct() {
		return product;
	}

	public void setProduct(Catentry product) {
		this.product = product;
	}

	public List<Catentry> getChild() {
		return child;
	}

	public void setChild(List<Catentry> child) {
		this.child = child;
	}

}
