package co.uk.app.commerce.catalog.catentry.service;

import java.util.Collection;

import co.uk.app.commerce.catalog.catentry.document.Catentry;

public interface CatentryService {
	
	Collection<Catentry> findCatentriesByCategoryIdentifier(String identifier);
	
	Collection<Catentry> findCatentriesByCategoryUrl(String url);
	
	Catentry findCatentryByURL(String url);
	
	Catentry findCatentryByPartnumber(String partnumber);
	
	void deleteCatentryById(String id);
	
	Catentry updateCatentry(Catentry catentry);
	
	Catentry persistCatentry(Catentry catentry);
	
}
