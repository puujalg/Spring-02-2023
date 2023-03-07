package ee.rainer.webshop.repository;

import ee.rainer.webshop.model.database.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
