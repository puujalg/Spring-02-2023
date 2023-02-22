package ee.rainer.kodune2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Card {

    private String suit;
    private String faceValue;
    private int pointValue;


    private boolean isHigher;
    private boolean isLower;
    private boolean isEqual;

}
