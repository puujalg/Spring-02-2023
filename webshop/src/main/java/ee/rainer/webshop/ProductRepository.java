package ee.rainer.webshop;

import org.springframework.data.jpa.repository.JpaRepository;

// CrudRepository
// PagingAndSortingRepository

public interface ProductRepository extends JpaRepository<Product, Long> {

}
