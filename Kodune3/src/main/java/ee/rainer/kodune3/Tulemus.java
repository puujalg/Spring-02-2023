package ee.rainer.kodune3;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tulemus")
public class Tulemus {

    @Id
    private Long tulemuseId;
    private Long sportlaseId;
    private Long spordialaId;
    private double tulemus;
    private double tulemusPunktid;
}