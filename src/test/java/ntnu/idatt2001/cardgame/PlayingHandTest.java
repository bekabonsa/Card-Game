package ntnu.idatt2001.cardgame;

import ntnu.idatt2001.cardgame.cards.PlayingCard;
import ntnu.idatt2001.cardgame.cards.PlayingHand;
import ntnu.idatt2001.cardgame.cards.DeckOfCards;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PlayingHandTest {

    @Test
    public void addToHandIncrease(){
        DeckOfCards deck = new DeckOfCards();
        PlayingHand hand = new PlayingHand();

        hand.addToHand(deck.dealHand(5));
        assertEquals(5,hand.getHand().size());
        hand.addToHand(deck.dealHand(5));
        assertEquals(10, hand.getHand().size());
    }

    @Test
    public void findHeartsInHand(){
        DeckOfCards deck = new DeckOfCards();
        PlayingHand hand = new PlayingHand();

        hand.addToHand(deck.dealHand(10));
        //hand.getHand().forEach(c-> System.out.println(c.getAsString()));
        //create a char List of all heart suits found in hand.
        List<Character> foundHearts = hand.getHand().stream().map(PlayingCard::getSuit).collect(Collectors.toList())
                .stream().filter(c-> c.charValue() == 'H').collect(Collectors.toList());

        //System.out.println("Found hearts: "+foundHearts);
        //use the findHearts() method from Class PlayingHand
        hand.findHearts();
        //System.out.println("Total hearts in hand: " + hand.getCardsOfHearts());
        String[] heartsInHand = hand.getCardsOfHearts().split(" ");
        assertEquals(heartsInHand.length, foundHearts.size());


    }

    @Test
    public void noHeartsInHandResults_NoHearts(){
        DeckOfCards deck = new DeckOfCards();
        PlayingHand hand = new PlayingHand();
        hand.addToHand(deck.dealHand(3));
        List<Character> foundHearts = hand.getHand().stream().map(PlayingCard::getSuit).collect(Collectors.toList())
                .stream().filter(c-> c.charValue() == 'H').collect(Collectors.toList());

        while(!foundHearts.isEmpty()){
            hand.setHand(new ArrayList<>());
            hand.addToHand(deck.dealHand(3));
            foundHearts = hand.getHand().stream().map(PlayingCard::getSuit).collect(Collectors.toList())
                    .stream().filter(c-> c.charValue() == 'H').collect(Collectors.toList());
        }

        hand.findHearts();

        assertEquals("No Hearts", hand.getCardsOfHearts());

    }

    @Test
    public void cannotAddEmptyListOfCards(){
        DeckOfCards deck = new DeckOfCards();
        PlayingHand hand = new PlayingHand();

        assertThrows(IllegalArgumentException.class,()->{
            hand.addToHand(new ArrayList<>());
        });
    }

    /**
     * there is a (1/52)^2 * 100% chance of this test failing.
     * (1/52)^2 * 100% = 0.036982248% chance of failing
     */
    @Test
    public void hasQueenOfSpades(){
        DeckOfCards deck = new DeckOfCards();
        PlayingHand hand = new PlayingHand();

        //add 52 cards to hand, and check if Queen of Spades is detected
        hand.addToHand(deck.dealHand(52));
        hand.calculateHasQueenOfSpades();
        assertTrue(hand.hasQueenOfSpades());

        //test with another deck with fewer cards, to minimize the chance of getting queen of spades
        DeckOfCards deck1 = new DeckOfCards();
        DeckOfCards deck2 = new DeckOfCards();
        PlayingHand hand1 = new PlayingHand();
        PlayingHand hand2 = new PlayingHand();


        hand1.addToHand(deck1.dealHand(1));
        hand2.addToHand(deck2.dealHand(1));
        hand2.checkHand();

        assertFalse((hand1.hasQueenOfSpades() && hand2.hasQueenOfSpades()));

    }

    @Test
    public void doesQueenInHandAndRandomCardWithSpadeCountAsS12(){
        PlayingHand hand = new PlayingHand();

        hand.addCard( new PlayingCard('C', 12));
        hand.checkHand();
        assertFalse(hand.hasQueenOfSpades());

        hand.addCard( new PlayingCard('S', 9));
        hand.checkHand();
        assertFalse(hand.hasQueenOfSpades());
    }


    @Test
    public void checkForFlushInFullDeck(){
        DeckOfCards deck = new DeckOfCards();
        PlayingHand hand = new PlayingHand();
        hand.addToHand(deck.dealHand(52));
        hand.checkHand();
        assertTrue(hand.hasFlush());

    }

    @Test
    public void checkForHeartsFlush(){
        PlayingHand hand = new PlayingHand();
        List<PlayingCard> cardsInHand = new ArrayList<>();
        char[] suit = {'S','H','D','C'};

        //check if 5 or more cards of the same color are considered a flush
        for(int i = 0; i<5; i++){
            cardsInHand.add(new PlayingCard('H', i));
            cardsInHand.add(new PlayingCard(suit[Math.abs(i-1)], i*2));
        }

        hand.setHand(cardsInHand);
        hand.checkHand();
        assertTrue(hand.hasFlush());

        //check if less than 5 cards are considered a flush
        cardsInHand.clear();
        PlayingHand hand1 = new PlayingHand();
        for(int i = 0; i<4; i++){
            cardsInHand.add(new PlayingCard('H', i));
        }

        hand1.setHand(cardsInHand);
        hand1.checkHand();
        assertFalse(hand1.hasFlush());


    }

    @Test
    public void checkForSpadesFlush(){
        PlayingHand hand = new PlayingHand();
        List<PlayingCard> cardsInHand = new ArrayList<>();
        char[] suit = {'S','H','D','C'};
        //check if 5 or more cards of the same color are c      onsidered a flush
        for(int i = 0; i<5; i++){
            cardsInHand.add(new PlayingCard('S', i));
            cardsInHand.add(new PlayingCard(suit[Math.abs(i-1)], i*2));
        }

        hand.setHand(cardsInHand);
        hand.checkHand();
        assertTrue(hand.hasFlush());
    }

    @Test
    public void checkForLessThan5cardsFlush(){
        PlayingHand hand = new PlayingHand();
        List<PlayingCard> cardsInHand = new ArrayList<>();
        //check if less than 5 cards are considered a flush
        PlayingHand hand1 = new PlayingHand();
        for(int i = 0; i<4; i++){
            cardsInHand.add(new PlayingCard('S', i));
        }

        hand1.setHand(cardsInHand);
        hand1.checkHand();
        assertFalse(hand1.hasFlush());
    }

    @Test
    public void checkForDiamondsFlush(){
        PlayingHand hand = new PlayingHand();
        List<PlayingCard> cardsInHand = new ArrayList<>();
        char[] suit = {'S','H','D','C'};
        //check if 5 or more cards of the same color are considered a flush
        for(int i = 0; i<5; i++){
            cardsInHand.add(new PlayingCard('D', i));
            cardsInHand.add(new PlayingCard(suit[Math.abs(i-1)], i*2));
        }
        hand.setHand(cardsInHand);
        hand.checkHand();
        assertTrue(hand.hasFlush());

        //check if less than 5 cards are considered a flush
        cardsInHand.clear();
        PlayingHand hand1 = new PlayingHand();
        for(int i = 0; i<4; i++){
            cardsInHand.add(new PlayingCard('D', i));
        }

        hand1.setHand(cardsInHand);
        hand1.checkHand();
        assertFalse(hand1.hasFlush());
    }

    @Test
    public void checkForClubsFlush(){
        PlayingHand hand = new PlayingHand();
        List<PlayingCard> cardsInHand = new ArrayList<>();
        char[] suit = {'S','H','D','C'};;
        //check if 5 or more cards of the same color are considered a flush
        for(int i = 0; i<5; i++){
            cardsInHand.add(new PlayingCard('C', i));
        }
        hand.setHand(cardsInHand);
        hand.checkHand();
        assertTrue(hand.hasFlush());

        //check if less than 5 cards are considered a flush
        cardsInHand.clear();
        PlayingHand hand1 = new PlayingHand();
        for(int i = 0; i<4; i++){
            cardsInHand.add(new PlayingCard('C', i));
            cardsInHand.add(new PlayingCard(suit[Math.abs(i-1)], i*2));
        }

        hand1.setHand(cardsInHand);
        hand1.checkHand();
        assertFalse(hand1.hasFlush());


    }






}
