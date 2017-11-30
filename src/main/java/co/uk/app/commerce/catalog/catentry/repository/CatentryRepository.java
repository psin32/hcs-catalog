package co.uk.app.commerce.catalog.catentry.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.uk.app.commerce.catalog.catentry.document.Catentry;

public interface CatentryRepository extends MongoRepository<Catentry, String> {

	Catentry findByUrl(String url);
	
	@Query(value = "{categories.identifier:?0}")
	Collection<Catentry> findByCategoryIdentifier(String identifier);
	
	Catentry findByPartnumber(String partnumber);
}
