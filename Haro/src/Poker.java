/*Justin Glenn
Attempt at poker kata
Professor Clark
CSC 439
2/25/18
Here I create a poker class which provides input from a file. Also where I debug and do fileinput.
 */
import java.io.File;
import java.util.Scanner;

public class Poker {

    public static void main(String arg[]) throws Exception {

        String[] deck = readHands();
        Hand black = new Hand();
        Hand white = new Hand();
        dealHands(black,white,deck);
        black.playHand(white);
    }
    //strings parsed by a colon in format 4H 4S AH 4D 10C:5C 2H 8D 10S 2D
    public static String[] readHands() throws Exception {

        String[] tempStr;
        File file = new File("input.txt");

        String content = new Scanner(file).useDelimiter("\\A").next();

        tempStr = content.split(":");

        return tempStr;
    }

    //assuming only two hands.
    public static void dealHands(Hand black, Hand white, String[] deck){


            black.setHandStr(deck[0]);
            white.setHandStr(deck[1]);
    }
}


