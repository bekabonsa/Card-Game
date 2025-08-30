package ntnu.idatt2001.cardgame.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents a full deck of cards (52 cards)
 * This class is responsible for creating a full deck and for dealing hands .
 */
public class DeckOfCards {


    private final char[] suit = { 'S', 'H', 'D', 'C' };
    private List<PlayingCard> deck = new ArrayList<>();



    /**
     * Constructor that creates all 52 cards needed for a full deck of cards.
     * 1 = Ace, 11=joker, 12=queen, 13=king
     * 0 = spades, 1 = hearts, 2 = clovers, 3 =diamonds
     */
    public DeckOfCards(){

        for(int i = 1; i < 14; i++){
            for(int u = 0; u < 4; u++){
                PlayingCard card = new PlayingCard(suit[u], i);
                deck.add(card);
            }
        }
    }

    /**
     * DealHand(n) is used to return "n" amount of cards from a DeckOfCards.
     * n = 1 will return a collection of 1 card and n=52 will return a collection of 52 cards.
     * Here, a collection in form of an ArrayList.
     * Throws an IllegalArgumentException if n is not between 1 and 52 (boundaries included).
     * n = 52 && n = 1 won't throw an exception, while anything that is not between these two will throw an exception
     * note: Dealt cards will be removed from the deck
     * note: if you eventually, in total draw more than 52 cards an IllegalArgumentException is thrown
     * @param n
     * @throws IllegalArgumentException
     * @return n amount of cards as a collection in an ArrayList.
     */
    public List<PlayingCard> dealHand(int n) throws IllegalArgumentException{
        Random random = new Random();
        ArrayList<PlayingCard> returnedCollection = new ArrayList<>();

        if (n <= 0 || n > 52){
            throw new IllegalArgumentException("Dealt cards must be between 1 and 52, [1,52]");
        }
        if (n > deck.size()){
            throw new IllegalArgumentException("Cannot deal more cards than is available in your deck");
        }
        for (int i = 0; i < n; i++){
            int rand = random.nextInt(deck.size());
            returnedCollection.add(deck.get(rand));
            deck.remove(rand);
        }

        return returnedCollection;

    }

    /**
     * returns an ArrayList of the PLaying Cards
     * @return
     */
    public List<PlayingCard> getDeck() {
        return deck;
    }

    /**
     * builds a String form of the entire deck
     */
    public String stringifyDeck(){
        StringBuilder strDeck = new StringBuilder();
        deck.forEach(c-> strDeck.append(c.getSuit()).append(c.getFace()+" "));
        return strDeck.toString();
    }


    /**
     * get char suit list
     * @return char[] of suits
     */
    public char[] getSuit() {
        return suit;
    }
}
