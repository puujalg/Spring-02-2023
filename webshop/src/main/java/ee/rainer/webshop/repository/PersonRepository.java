package ee.rainer.webshop.repository;

import ee.rainer.webshop.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
