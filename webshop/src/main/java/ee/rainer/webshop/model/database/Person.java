package ee.rainer.webshop.model.database;

//postgreSQL puhul Userit ei sa teha

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean admin;

}
