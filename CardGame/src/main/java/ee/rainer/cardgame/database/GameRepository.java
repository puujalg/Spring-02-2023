package ee.rainer.cardgame.database;

import ee.rainer.cardgame.game.Game;
import ee.rainer.cardgame.game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByPlayer(Player player);
    List<Game> findAllByOrderByDuration();
    List<Game> findAllByOrderByCorrectGuessesDesc();
}
