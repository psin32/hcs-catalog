package co.uk.app.commerce.catalog.category.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.uk.app.commerce.catalog.category.document.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
