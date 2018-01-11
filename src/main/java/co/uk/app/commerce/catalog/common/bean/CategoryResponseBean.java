package co.uk.app.commerce.catalog.common.bean;

import java.util.Collection;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.catentry.document.Catentry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseBean {

	private Category category;
	
	private Collection<Category> subcategories;
	
	private Collection<Catentry> catentries;
}
