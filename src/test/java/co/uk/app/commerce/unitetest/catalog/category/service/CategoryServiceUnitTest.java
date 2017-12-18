package co.uk.app.commerce.unitetest.catalog.category.service;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.uk.app.commerce.catalog.category.document.Category;
import co.uk.app.commerce.catalog.category.service.CategoryService;
import co.uk.app.commerce.catalog.common.bean.Description;
import co.uk.app.commerce.unitetest.catalog.base.AbstractCatalogUnitTest;

public class CategoryServiceUnitTest extends AbstractCatalogUnitTest {

	@Autowired
	private CategoryService categoryService;

	@Before
	public void setup() {
		importJSON("category", "src/test/resources/category.txt");
	}

	@Test
	public void testSaveCategory() throws Exception {
		Category category = new Category();
		category.setIdentifier("TestCameras");
		category.setPublished(1);
		category.setSequence(1);
		category.setDisplay(1);
		category.setTopnav(true);

		Description description = new Description();
		description.setName("Test Camera");
		description.setShortdescription("Test Camera Short Description");
		description.setLongdescription("Test Camera Long Description");
		category.setDescription(description);

		Category savedCategory = categoryService.persistCategory(category);
		System.out.println("=========================================");
		System.out.println(savedCategory.getId());
		Collection<Category> catColl = categoryService.findAllCategories();
		Iterator<Category> categories = catColl.iterator();
		while (categories.hasNext()) {
			Category allCategory = categories.next();
			System.out.println(allCategory.getId());
			System.out.println(allCategory.getIdentifier());
			System.out.println(allCategory.getDescription().getName());
			System.out.println(allCategory.getDescription().getShortdescription());
			System.out.println(allCategory.getDescription().getLongdescription());
		}
		System.out.println("=========================================");
	}
}
