package ee.rainer.webshop.repository;

import ee.rainer.webshop.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// CrudRepository
// PagingAndSortingRepository

public interface ProductRepository extends JpaRepository<Product, Long> {

    //List<Product> findAllBy(Category category);

}
