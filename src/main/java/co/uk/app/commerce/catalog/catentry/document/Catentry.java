package co.uk.app.commerce.catalog.catentry.document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.uk.app.commerce.catalog.common.bean.Association;
import co.uk.app.commerce.catalog.common.bean.Attributes;
import co.uk.app.commerce.catalog.common.bean.CatentryType;
import co.uk.app.commerce.catalog.common.bean.Description;
import co.uk.app.commerce.catalog.common.bean.Image;
import co.uk.app.commerce.catalog.common.bean.ListPrice;
import co.uk.app.commerce.catalog.common.bean.OfferPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "catentry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catentry {

	@Id
	private String id;

	@Indexed(unique = true)
	private String partnumber;

	private Description description;

	private List<Image> thumbnail;

	private List<Image> fullimage;

	private CatentryType type;

	private Integer published;

	private Integer buyable;

	private String url;

	private String lastupdate = new SimpleDateFormat("dd-MM-yy HH:mm:ss.SS").format(new Date());

	private String startdate;

	private String enddate;

	private List<Association> childitems;

	private List<Attributes> attributes;

	private List<ListPrice> listprice;

	private List<OfferPrice> offerprice;
}
