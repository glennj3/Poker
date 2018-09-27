/*
Attempt at poker kata
CSC 439
2/25/18
Here I have my test class. 
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {


    Hand Black = new Hand("4H 2H 3H 5H 6H");

    @Test
    public void getHand() {
        assertEquals("4H 2H 3H 5H 6H", Black.getHand());
    }

    @Test
    public void getRank() {
        Hand temp = new Hand();
        assertEquals(0, temp.getRank());
    }

    @Test
    public void setHandStr() {
        Black.setHandStr("2H 3D 5S 9C KD");
        assertEquals("2H 3D 5S 9C KD", Black.getHand());
    }

    @Test
    public void getHighCard() {
        Black.setHandStr("2H 3D 5S 9C KD");
        assertEquals(13, Black.getHighCard());
    }

    @Test
    public void isHighCard() {
        Black.setHandStr("2H 7D 10S 9C KD");
        assertEquals(true, Black.isHighCard());
    }

    @Test
    public void isPair() {
        Black.setHandStr("2H 2D 5S 9C KD");
        assertEquals(true, Black.isPair());
    }

    @Test
    public void isThreeOfAKind() {
        Black.setHandStr("2H 2D 2S 9C KD");
        assertEquals(true, Black.isThreeOfAKind());
    }

    @Test
    public void isFourOfAKind() {
        Black.setHandStr("2H 2D 2S 2C KD");
        assertEquals(true, Black.isFourOfAKind());
    }

    @Test
    public void isFlush() {
        Black.setHandStr("2H 3H 5H AH KH");
        assertEquals(true, Black.isFlush());
    }


    @Test
    public void isTwoPair() {
        Black.setHandStr("2H 2D 5S 5C KD");
        assertEquals(true, Black.isTwoPair());
    }

    @Test
    public void isStraight() {
        Black.setHandStr("2H 3D 4S 5C 6D");
        assertEquals(true, Black.isStraight());
    }

    @Test
    public void isFullHouse() {
        Black.setHandStr("2H 2D 2S 3C 3D");
        assertEquals(true, Black.isFullHouse());
    }

    @Test
    public void isRoyalFlush() {
        Black.setHandStr("10D JD QD KD AD");
        assertEquals(true, Black.isRoyalFlush());
    }

    @Test
    public void playHand() {
        Hand Black = new Hand("4H 2H 3H 5H AH");
        Hand White = new Hand("5C 2H 8D 10S 2D");

        assertEquals(1,Black.playHand(White));
    }

}
