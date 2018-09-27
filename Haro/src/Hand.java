/*
Attempt at poker kata
CSC 439
2/25/18
Here I create my Hand class for the poker kata.
 */
//I import arrays to use the sort.
import java.util.Arrays;

//I create a class called hand.
public class Hand {

    //It has five values with the class. HighestConsecutive, which is highest occurence of a card value,
    //Highcard which is the highest value of a card. Handstr holds the initial string from a txt file.
    //rank which is the final rank of the hand. SuitArr holds the suits for each card. IntArr holds int value.
    private int highestConsecutive = 1;
    private int highCard = 0;
    private String handStr;
    private int rank = 0;
    private char[] suitArr = new char[5];
    private int[] intArr = new int[5];

        //I make a no args constructor that won't really be used much.
        public Hand(){}

        //I make a constructor that takes a string value in the form 4D 5C 2S 7D KD. Case sensitivity is important.
        //It will then use methods to find the values in the cards and determine a rank.
        public Hand(String str){

            setHandStr(str);
            findSuits();
            findCardValue();
            findHighestConsecutive();
            determineRank();
        }
        //Getter for the rank variable.
        public int getRank(){
            return this.rank;
        }

        //Setter for handStr string. Like the constructor, it has to redetermine rank if changed.
        public void setHandStr(String handStr) {
            this.handStr = handStr;
            findSuits();
            findCardValue();
            findHighestConsecutive();
            determineRank();
        }

        //Getter for handStr, returns a string.
        public String getHand(){
            return this.handStr;
        }
        //Getter for highcard variable. returns an int.
         public int getHighCard() {
            return highCard;
         }
         //Setter for high card variable.
         private void setHighCard(int highCard) {
            this.highCard = highCard;
        }
        //private setter for rank, should only be used internally.
        private void setRank(int rank) {

                this.rank = rank;
        }
        //Setter for intArr that holds card values(note:these will be sorted)
        private void setIntArr(int[] intArr) {
             this.intArr = intArr;

        }
        //getter for IntArray.
        public int[] getIntArr() {
             return intArr;
         }
        //setter for the suitArr.
         public void setSuitArr(char[] suitArr) {
            this.suitArr = suitArr;
        }
        //getter for the suit array
        public char[] getSuitArr() {
            return suitArr;
        }

        //getter for highest consecutive
         public int getHighestConsecutive() {
            return highestConsecutive;
         }
        //setter for highest consecutive
        public void setHighestConsecutive(int highestConsecutive) {
            this.highestConsecutive = highestConsecutive;
         }

         //findSuits will take the handStr and parse out the suits into and array of chars.
    private void findSuits(){
            //index keeps track of temp index location in iteration
            int index = 0;

            //temp will hold the new array
            char[] temp = new char[5];

            //for loop checks handstr for the different suits and if they are there puts them into array
            for (int i = 0; i < handStr.length(); i++){

                if(handStr.charAt(i) == 'C' || handStr.charAt(i) == 'D'
                        || handStr.charAt(i) == 'S' || handStr.charAt(i) == 'H'){
                    temp[index] = handStr.charAt(i);
                    index++;
                }
        }

        setSuitArr(temp);

    }

    //findCardValue will add the card values to the intArr and sort it.
    //if the value is a royal, then they will be 11,12,13,14 respectively.
    private void findCardValue() {

            int testArr[] = new int[5];

            for(int i = 0,j=0; i < this.handStr.length(); i+=3,j++){

                String subOfElement = this.handStr.substring(i,i + 1);
                if(subOfElement.equals("J") )
                    testArr[j] = 11;
                else if(subOfElement.equals("Q"))
                    testArr[j] = 12;
                else if(subOfElement.equals("K"))
                    testArr[j] = 13;
                else if(subOfElement.equals("A"))
                    testArr[j] = 14;

                else {
                    testArr[j] = Integer.parseInt(subOfElement);
                    if (testArr[j] == 1) {
                        testArr[j] = 10;
                        i++;
                    }
                }
            }

            //TESTARR IS SORTED
            Arrays.sort(testArr);

            setIntArr(testArr);

            //I also set high card to last element in array after it is sorted.
            setHighCard(testArr[4]);

    }

    //determineRank decides which rank it will be going top down from highest to lowest.
    //The reason I have to go top down when determining ranks is a false positive.
    //Two pairs will match full house for instance.
    public void determineRank(){
        //ranks highcard 0, pair 1, twopair 2, three of a kind 3, straight 4, flush 5,
        //full house 6, four of a kind 7 , straight flush 8, royal flush 9
        //I should start top down.

        //Royal Flush
        if(isRoyalFlush())
            setRank(9);
        //Straight flush
        else if(isFlush() && isStraight())
            setRank(8);
        //four of a kind
        else if(isFourOfAKind())
            setRank(7);
        //full house
        else if( isFullHouse())
            setRank(6);
        //flush
        else if(isFlush())
            setRank(5);
        //straight
        else if(isStraight())
            setRank(4);
        //three of a kind
        else if(isThreeOfAKind())
            setRank(3);
        //twoPair
        else if(isTwoPair())
            setRank(2);
        //pair
        else if(isPair())
            setRank(1);
        //highcard
        else
            setRank(0);

    }

    //findHighestConsecutive should really be occurence, but i'm lazy and am going to leave it.
    //But it finds the highest instance of a value.
    public void findHighestConsecutive(){

        int maxSame = 1;
        int tempSame = 1;

        int testArr[] = getIntArr();

        for(int i = 0; i < testArr.length; i++){

            for(int j = i + 1; j < testArr.length; j++){
                if(testArr[i] == testArr[j]){
                    tempSame++;
                }
            }
            if(tempSame > maxSame) {
                maxSame = tempSame;
            }

            tempSame = 1;
        }

        setHighestConsecutive(maxSame);
    }

    //If highest consecutive is 1, then there were no other cards, so it's a highcard hand.
    public boolean isHighCard() {

        return getHighestConsecutive() == 1;
    }

    //is pair see if highestConsecutive is 2.
    public boolean isPair() {

        return getHighestConsecutive() == 2;
    }
    //isTwoPair checks for all instances of two pairs in a hand that is sorted.
    //Will be a false positive with full house which is why I go top down.
    public boolean isTwoPair(){
        int temp[] = getIntArr();

        if(temp[0] == temp[1] && temp[2] == temp[3])
            return true;

        else if(temp[0] == temp[1] && temp[3] == temp[4])
            return true;

        else if(temp[1] == temp[2] && temp[3] == temp[4])
            return true;

        else return false;
    }
    //isThree of a kind checks if there were 3 instances of a number.
    public boolean isThreeOfAKind() {

        return getHighestConsecutive() == 3;
    }
    //isThree of a kind checks if there were 3 instances of a number.
    public boolean isFourOfAKind() {

        return getHighestConsecutive() == 4;
    }
    //Is straight sees if the values in the intArr are consecutive, if so, then it is a straight.
    public boolean isStraight() {
        int[] temp = getIntArr();

        return  temp[1] == temp[0] + 1 && temp[2] == temp[1] + 1 && temp[3] == temp[2] + 1 && temp[4] == temp[3] + 1;
    }

    //see if all the suits are the same from the getSuitArr
    public boolean isFlush() {
            char[] temp = getSuitArr();

        return temp[0] == temp[1] && temp[1] == temp[2] && temp[2] == temp[3] && temp[3] == temp[4];
    }

    //Check both instances of full house in sorted hand. ie XXXYY or YYXXX.
    public boolean isFullHouse(){
            int temp[] = getIntArr();
            if(temp[0] == temp[1] && temp[1] == temp[2] && temp[3] == temp[4])
                return true;

            else if(temp[0] == temp[1] && temp[2] == temp[3] && temp[3]== temp[4] )
                return true;

            else return false;
    }

    //If a hand is both a flush and a straight with the initial value in the sorted array starting at 10,
    //then it is royal and returns true.
    public boolean isRoyalFlush(){
            int[] testArr = getIntArr();

        return isFlush() && isStraight() && testArr[0] == 10;
    }

    //the one being acted upon is assumed black. 1 means black wins, -1 means white wins, 0 means tie.
    public int playHand(Hand hand){

            if( this.getRank() > hand.getRank()){
                System.out.println();
                System.out.println("Black: " + this.getHand() + " White: " + hand.getHand());
                System.out.println("Black wins!");
                return 1;
            }

            else if(this.getRank() < hand.getRank()){
                System.out.println();
                System.out.println("Black: " + this.getHand() + " White: " + hand.getHand());
                System.out.println("White wins!");
                return -1;
            }
            else{
                System.out.println();
                System.out.println("Black: " + this.getHand() + " White: " + hand.getHand());

                    if(this.getHighCard() > hand.getHighCard()){
                        System.out.println("Black Wins!");
                        return 1;
                    }
                    else if(hand.getHighCard() > this.getHighCard()){
                        System.out.println("white Wins!");
                    }
                System.out.print("Tie!");
                return 0;
            }

    }

}


