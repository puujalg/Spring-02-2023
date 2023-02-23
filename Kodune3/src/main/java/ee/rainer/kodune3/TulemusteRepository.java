package ee.rainer.kodune3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TulemusteRepository extends JpaRepository<Tulemus, Long> {

    List<Tulemus> findAllBySportlaseId(Long id);

}
