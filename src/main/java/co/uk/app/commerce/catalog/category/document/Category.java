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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
