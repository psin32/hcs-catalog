package co.uk.app.commerce.catalog.category.document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.uk.app.commerce.catalog.category.bean.Association;
import co.uk.app.commerce.catalog.category.bean.Description;
import co.uk.app.commerce.catalog.category.bean.Image;

@Document(collection = "category")
public class Category {

	@Id
	private String id;

	private String identifier;

	private Description description;

	private List<Image> thumbnail;

	private List<Image> fullimage;

	private Integer published;

	private Integer display;

	private Integer sequence;

	private boolean topnav;

	private String parentcategoryidentifier;

	private List<Association> association;

	private String lastupdate = new SimpleDateFormat("dd-MM-yy HH:mm:SS").format(new Date());

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

	public boolean isTopnav() {
		return topnav;
	}

	public void setTopnav(boolean topnav) {
		this.topnav = topnav;
	}

	public String getParentcategoryidentifier() {
		return parentcategoryidentifier;
	}

	public void setParentcategoryidentifier(String parentcategoryidentifier) {
		this.parentcategoryidentifier = parentcategoryidentifier;
	}

	public List<Association> getAssociation() {
		return association;
	}

	public void setAssociation(List<Association> association) {
		this.association = association;
	}

}
