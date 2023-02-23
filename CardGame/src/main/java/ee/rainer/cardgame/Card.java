package ee.rainer.cardgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public class Card {

    private Suit suit;
    private Rank rank;
    private int value;

    public Card() {
        this.suit = Suit.randomSuit();
        this.rank = Rank.randomRank();
    }

    private int determineValue() {
        return switch (rank) {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            default -> 10;
        };
    }

    /**
    String[] suits = {"HEARTS","DIAMONDS","SPADES","CLUBS"};

    public String randomSuit () {
        Random rand = new Random();
        return suits[rand.nextInt(suits.length)];
    }
     **/


}
