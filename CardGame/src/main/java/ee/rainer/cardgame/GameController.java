package ee.rainer.cardgame;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class GameController {

    private Card card;

    private LocalDateTime roundStartTime;

    private LocalDateTime roundCurrentTime;

    @GetMapping("start")
    public Card startRound() {
        roundStartTime = LocalDateTime.now();

        roundCurrentTime= LocalDateTime.now();

        //Duration duration = Duration.between(roundStartTime, roundCurrentTime);

        //System.out.println(duration);
        if (card == null) {
            card = new Card();
        }
        return card;
    }

    @GetMapping("guess/{user}")
    public String makeGuess(@PathVariable String userGuess) {

        LocalDateTime roundStartTimeLater = roundStartTime.plusSeconds(10);
        LocalDateTime actualTime = LocalDateTime.now();

        if (actualTime.isAfter(roundStartTimeLater)) {
            return "Aeg läbi!";
        }

        /**
        if (tenSecondsLater.isAfter(roundStartTime)) {
            return "Too late! No answer within 10 seconds."
        }
         **/

        Card newCard = new Card();
        String response;

        if (userGuess.equals("lower") && card.getValue() < newCard.getValue()) {
            response = "Correct!";
        } else {
            response = "Wrong!";
        }

        card = newCard;
        return response;
    }

    /**
    @GetMapping("new-round")
    public Card newRound() {
        return card;
    }
    **/

}
