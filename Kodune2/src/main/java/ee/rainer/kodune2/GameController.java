package ee.rainer.kodune2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    boolean gameStarted = false;
    Card lastCard = new Card();
    Card newCard = new Card();

    @GetMapping("startgame")
    public Card startRound () {
        if(gameStarted == false) {
            gameStarted = true;
            lastCard = CardGenerator.generateRandomCard();
            return lastCard;
        } else {
            // ...
        }
        return null;
    }

    @GetMapping("stopgame")
    public void stopRound () {
        if(gameStarted == true) {
            gameStarted = false;

        } else {
            // ...
        }
        // return null;
    }

    /**
    @GetMapping("arva/{arvamus}")
    public String userGuess () {

    }
     **/

    @GetMapping("draw")
    public Card drawNewCard () {
        if(gameStarted == true) {
            newCard = CardGenerator.generateRandomCard();
            // If new card is same as last the one, draw a new card
            while (lastCard.getSuit().equals(newCard.getSuit()) && lastCard.getFaceValue().equals(newCard.getFaceValue())) {
                newCard = CardGenerator.generateRandomCard();
            }
            // decide if lower, higher or equal than the last card
            if(newCard.getPointValue() > lastCard.getPointValue()) newCard.setHigher(true);
            if(newCard.getPointValue() < lastCard.getPointValue()) newCard.setLower(true);
            if(newCard.getPointValue() == lastCard.getPointValue()) newCard.setEqual(true);

            lastCard = newCard;
            return newCard;
        } else {
            // ...
        }
        return null;
    }

}
