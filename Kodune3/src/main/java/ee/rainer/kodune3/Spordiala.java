package ee.rainer.kodune3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Spordiala")
public class Spordiala {

    @Id
    private long id;
    private String spordialaNimi;

    private double a;
    private double b;
    private double c;

    private boolean jooksuala;
    private boolean valjakuala;
}

/**
 *  100 - 100m Jooks
 *  101 - Kaugushupe
 *  102 - Kuulitouge
 *  103 - Korgushupe
 *  104 - 400m Jooks
 *  105 - 110m Tokkejooks
 *  106 - Kettaheide
 *  107 - Teivashupe
 *  108 - Odavise
 *  109 - 1500m Jooks
 */
