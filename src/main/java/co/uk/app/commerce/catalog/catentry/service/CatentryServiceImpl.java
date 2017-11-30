package co.uk.app.commerce.catalog.catentry.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.uk.app.commerce.catalog.catentry.document.Catentry;
import co.uk.app.commerce.catalog.catentry.repository.CatentryRepository;

@Component
public class CatentryServiceImpl implements CatentryService {

	@Autowired
	private CatentryRepository catentryRepository;

	@Override
	public Collection<Catentry> findCatentriesByCategoryIdentifier(String identifier) {
		return catentryRepository.findByCategoryIdentifier(identifier);
	}

	@Override
	public Catentry findCatentryByURL(String url) {
		return catentryRepository.findByUrl(url);
	}

	@Override
	public void deleteCatentryById(String id) {
		catentryRepository.delete(id);
	}

	@Override
	public Catentry updateCatentry(Catentry catentry) {
		return catentryRepository.save(catentry);
	}

	@Override
	public Catentry persistCatentry(Catentry catentry) {
		return catentryRepository.insert(catentry);
	}

	@Override
	public Catentry findCatentryByPartnumber(String partnumber) {
		return catentryRepository.findByPartnumber(partnumber);
	}

}
