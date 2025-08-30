package ntnu.idatt2001.cardgame.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that manages a playing hand. Operations on a given hand is performed by this class
 */
public class PlayingHand {


    private List<PlayingCard> hand = new ArrayList<>();
    private int faceSum;
    private String cardsOfHearts;
    private boolean flush;
    private boolean hasQueenOfSpades;


    /**
     * Adds cards to a hand.
     * @param cards
     */
    public void addToHand(List<PlayingCard> cards) throws IllegalArgumentException{
        if(cards.isEmpty()){
            throw new IllegalArgumentException("Cannot add an empty list of cards");
        }
        cards.forEach(c-> hand.add(c));
    }


    /**
     * Checks the value of a hand for:
     *    -faceSum, the sum of all the cards on a hand, sum of the card numbers
     *    -Hearts, cards that are of the type hearts, are collected and stored
     *    -QueenOfSpades, checks if the queen of spades is on a hand
     *    -Flush, checks if 5 cards are of the same color
     *    note: throws an IllegalArgumentException if hand is empty
     *    @throws IllegalArgumentException
     */

    public void checkHand() throws IllegalArgumentException{
        if(hand.isEmpty()){
            throw new IllegalArgumentException("Cannot check an empty deck");
        }

        calculateFaceSum();
        findHearts();
        calculateHasQueenOfSpades();
        checkForFlush();

    }

    /**
     * The sum of all the cards in a hand
     * sets the sum to 0 if there are no cards in your hand
     */
    public void calculateFaceSum(){
        if(hand.isEmpty()){
             faceSum = 0;
        }
        else{
            faceSum = 0;
            hand.forEach(c -> faceSum+=c.getFace());
        }

    }

    /**
     *  Checks if a hand contains cards with hearts, if it does add them to the String cardsOfHearts.
     *  Example = "H12 H9 H1" -> heart 12, heart 9, heart 1.
     *  sets cardsOfHearts to "No hearts" if there are no hearts in a hand
     */
    public void findHearts(){

        if(hand.isEmpty() || hand == null){
            cardsOfHearts = "No Hearts";
            return;
        }

        List<PlayingCard> heartsList = new ArrayList<>();
        heartsList = hand.stream().filter(c -> c.getSuit() == 'H').collect(Collectors.toList());

        StringBuilder str = new StringBuilder();
        heartsList.forEach(c-> str.append(c.getSuit()).append(c.getFace()+" "));

        if(str.toString().isEmpty()){
            cardsOfHearts = "No Hearts";
            return;
        }
        else{
            cardsOfHearts = str.toString();
        }

    }

    /**
     * sets the value of hasQueenOfSpades using java.util.stream
     */
    public void calculateHasQueenOfSpades(){
        boolean qSpades = hand.stream().anyMatch(c-> (c.getSuit() == 'S')&&(c.getFace() == 12));
        if(qSpades) hasQueenOfSpades = true;
        else{
            hasQueenOfSpades = false;
        }
    }

    /**
     * checks for a flush in your hand
     * 5 cards with the same suit will count as a flush
     *
     */
    public void checkForFlush(){
        String suitString = stringifyHand();
        int S = (int) suitString.chars().filter(ch -> ch == 'S').count();
        int H = (int) suitString.chars().filter(ch -> ch == 'H').count();
        int D = (int) suitString.chars().filter(ch -> ch == 'D').count();
        int C = (int) suitString.chars().filter(ch -> ch == 'C').count();

        if(S>=5 ||H>=5 || D>=5 || C>=5) flush = true;
        else{
            flush = false;
        }
    }


    /**
     *
     */
    public void isFourOfKind(){
        HashMap<String, String> faceAndAmount = new HashMap<>();
        String str = stringifyHand();
        String[] strSplit = str.split("");

        for(String cStr: strSplit){
            faceAndAmount.put(String.valueOf(cStr.charAt(0)),
                    String.valueOf(cStr.charAt(1))+ cStr.charAt(2));
        }

    }

    public String stringifyHand(){
        StringBuilder strHand = new StringBuilder();
        hand.forEach(c-> strHand.append(c.getSuit()).append(c.getFace()+" "));
        return strHand.toString();
    }

    public List<PlayingCard> getHand() {
        return hand;
    }

    public int getFaceSum() {
        return faceSum;
    }

    /**
     * getter method for returning the String format of cards in hand that have a heart
     * If there are no cards with a heart in hand, return "No Hearts"
     * @return cards with hearts
     */
    public String getCardsOfHearts() {
        findHearts();
        return cardsOfHearts;
    }

    public void addCard(PlayingCard card){
        hand.add(card);
    }

    public boolean hasFlush() {
        return flush;
    }

    public boolean hasQueenOfSpades() {
        return hasQueenOfSpades;
    }

    public void setHand(List<PlayingCard> hand) {
        this.hand = hand;
    }


}
