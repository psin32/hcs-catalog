package co.uk.app.commerce.catalog.catentry.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.uk.app.commerce.catalog.catentry.document.Catentry;

public interface CatentryRepository extends MongoRepository<Catentry, String> {

	Catentry findByUrl(String url);

	@Query(value = "{categories.identifier:?0}")
	Collection<Catentry> findByCategoryIdentifier(String identifier);

	@Query(value = "{categories.url:?0}")
	Collection<Catentry> findByCategoryUrl(String url);

	Catentry findByPartnumber(String partnumber);

	@Query("{partnumber: {$in: ?0}, type : ?1, buyable : 1}")
	Collection<Catentry> findByCatentryPartnumber(List<String> partnumber, String type);
	
	@Query(value = "{childitems.identifier:?0}")
	Catentry findParentProduct(String partnumber);
}
