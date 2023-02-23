package ee.rainer.cardgame;

import java.util.Random;

public enum Suit {

    HEARTS, DIAMONDS, SPADES, CLUBS;

    public static Suit randomSuit() {

        Random rand = new Random();
        return values()[rand.nextInt(values().length)];

    }

}
