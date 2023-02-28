package ee.rainer.cardgame.database;

import ee.rainer.cardgame.game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
