package ee.rainer.cardgame.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    GameService gameService;


    @GetMapping("player/{playerName}")
    public String player(@PathVariable String playerName) {
        return gameService.savePlayer(playerName);
    }

    @GetMapping("start")
    public Card startRound() {
        return gameService.getCard();
    }

    @GetMapping("guess/{choice}")
    public String makeGuess(@PathVariable String choice) {
        return gameService.userGuess(choice);
    }

}
