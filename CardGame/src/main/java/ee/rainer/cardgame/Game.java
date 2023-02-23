package ee.rainer.cardgame;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;
    private int correctGuesses;
    private LocalDateTime duration;

    @OneToOne
    private Player player;

}
