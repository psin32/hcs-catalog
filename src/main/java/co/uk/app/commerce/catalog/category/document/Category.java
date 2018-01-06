package co.uk.app.commerce.catalog.category.document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.uk.app.commerce.catalog.common.bean.Association;
import co.uk.app.commerce.catalog.common.bean.Description;
import co.uk.app.commerce.catalog.common.bean.Image;

@Document(collection = "category")
public class Category {

	@Id
	private String id;

	@Indexed(unique = true)
	private String identifier;

	private Description description;

	private List<Image> thumbnail;

	private List<Image> fullimage;

	private Integer published;

	private Integer display;

	private Integer sequence;

	private Boolean topnav;

	private Boolean topCategory;

	private List<Association> childcategories;

	private List<Association> products;

	private String url;

	private String lastupdate = new SimpleDateFormat("dd-MM-yy HH:mm:ss.SS").format(new Date());

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public List<Image> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(List<Image> thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<Image> getFullimage() {
		return fullimage;
	}

	public void setFullimage(List<Image> fullimage) {
		this.fullimage = fullimage;
	}

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setTopnav(Boolean topnav) {
		this.topnav = topnav;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getTopCategory() {
		return topCategory;
	}

	public void setTopCategory(Boolean topCategory) {
		this.topCategory = topCategory;
	}

	public List<Association> getChildcategories() {
		return childcategories;
	}

	public void setChildcategories(List<Association> childcategories) {
		this.childcategories = childcategories;
	}

	public List<Association> getProducts() {
		return products;
	}

	public void setProducts(List<Association> products) {
		this.products = products;
	}

	public Boolean getTopnav() {
		return topnav;
	}

}
