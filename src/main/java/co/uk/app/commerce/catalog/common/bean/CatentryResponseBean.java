package co.uk.app.commerce.catalog.common.bean;

import java.util.List;

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
public class CatentryResponseBean {

	private Catentry product;

	private List<Catentry> child;

	private String defaultItem;

	private Category category;
}
