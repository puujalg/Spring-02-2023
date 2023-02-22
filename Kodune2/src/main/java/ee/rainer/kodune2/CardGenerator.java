package ee.rainer.kodune2;

import java.util.Random;


public class CardGenerator {

    // Construct random card
    public static Card generateRandomCard() {
        Card card = new Card();

        card.setSuit(generateSuit());
        card.setPointValue(generatePointValue());
        card.setFaceValue(generateFaceValue(card.getPointValue()));

        return card;
    }

    // Generate random card suit
    public static String generateSuit() {
        String suit = "";
        Random random = new Random();
        int rand = random.nextInt(4);
        if(rand == 0) suit = "Hearts";
        if(rand == 1) suit = "Diamonds";
        if(rand == 2) suit = "Clubs";
        if(rand == 3) suit = "Spades";
        return suit;
    }

    // Generate random point value from 2 to 10
    public static int generatePointValue() {
        Random random = new Random();
        int pointValue = 2+random.nextInt(9);
        return pointValue;
    }

    // Assign face value based on previously generated point value
    public static String generateFaceValue(int pointValue) {
        String faceValue = "";
        if(pointValue <= 9) {
            faceValue = Integer.toString(pointValue);
        }
        else {
            faceValue = generateFace();
        }
        return faceValue;
    }

    // If point value was 10, generate random facecard
    public static String generateFace() {
        String face = "";
        Random random = new Random();
        int rand = random.nextInt(5);
        if(rand == 0) face = "Jack";
        if(rand == 1) face = "Queen";
        if(rand == 2) face = "King";
        if(rand == 3) face = "Ace";
        if(rand == 4) face = "10";
        return face;
    }

}
