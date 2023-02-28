package ee.rainer.webshop.repository;

import ee.rainer.webshop.model.Category;
import ee.rainer.webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// CrudRepository
// PagingAndSortingRepository

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBy(Category category);

}
