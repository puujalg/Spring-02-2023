package ee.rainer.kodune4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Location {

    @Id
    //@GeneratedValue
    private Long id;

    private String address;
    private String name;
    private String earliestTime;
    private String latestTime;

}
