package ee.rainer.webshop.model.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orders")
@SequenceGenerator(name="seq", initialValue = 223344)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private Date created;
    @ManyToOne
    private Person person;
    @ManyToMany
    private List<Product> orderProducts;
    private boolean paid;
    private  double totalSum;

}
