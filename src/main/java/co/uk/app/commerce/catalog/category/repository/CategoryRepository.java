package co.uk.app.commerce.catalog.category.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.uk.app.commerce.catalog.category.document.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

	Category findByIdentifier(String identifier);
	
	Collection<Category> findByTopnavIsTrue();

	Category findByUrl(String url);
	
	Category findById(String id);

	@Query(value = "{parentcategories.identifier:?0}")
	List<Category> findAllSubcategories(String identifier);
	
	@Query(value = "{products.identifier:?0}")
	Category findCategoryByProductIdentifier(String partnumber);

}
