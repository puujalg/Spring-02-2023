package ee.rainer.webshop.repository;

import ee.rainer.webshop.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
