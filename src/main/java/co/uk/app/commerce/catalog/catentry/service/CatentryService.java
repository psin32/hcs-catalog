package co.uk.app.commerce.catalog.catentry.service;

import java.util.Collection;
import java.util.List;

import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.common.bean.Association;
import co.uk.app.commerce.catalog.common.bean.CatentryResponseBean;

public interface CatentryService {

	Collection<Catentry> findCatentriesByCategoryIdentifier(String identifier);

	Collection<Catentry> findAllCatentriesForCategory(List<Association> products);

	Catentry findCatentryByURL(String url);

	CatentryResponseBean findProductData(String url);

	Catentry findCatentryByPartnumber(String partnumber);

	void deleteCatentryById(String id);

	Catentry updateCatentry(Catentry catentry);

	Catentry persistCatentry(Catentry catentry);

	void updateUrl();

}
