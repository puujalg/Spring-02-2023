package ee.rainer.kodune3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sportlane")
public class Sportlane {

    @Id
    private Long id;
    private String nimi;
    private String riik;
    private int vanus;

}
