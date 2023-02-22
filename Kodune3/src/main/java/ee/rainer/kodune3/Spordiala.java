package ee.rainer.kodune3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Spordiala {

    @Id
    private long id;
    private String spordialaNimi;

    private double a;
    private double b;
    private double c;
}
