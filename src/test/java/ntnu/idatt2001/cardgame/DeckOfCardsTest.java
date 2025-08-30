package ntnu.idatt2001.cardgame;

import ntnu.idatt2001.cardgame.cards.PlayingCard;
import ntnu.idatt2001.cardgame.cards.DeckOfCards;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckOfCardsTest {


    @Test
    public void areThere4VariationsOfAllFaces(){
        DeckOfCards deckOfCards = new DeckOfCards();
        StringBuilder str = new StringBuilder();
        String deckString = deckOfCards.stringifyDeck();
        List<String> deckCards = List.of(deckString.split(" "));
        assertEquals(52, deckCards.size());
        //loop for each 13 cards
        for(int i = 1; i < 14; i++){
           for(int u = 0; u < 4; u++){
               str.append(deckOfCards.getSuit()[u]).append(i);
               assertTrue(deckCards.contains(str.toString()));
               str.setLength(0);
           }
        }
    }

    @Test
    public void isGetDeckOfCardsReturning_DeckSize_AmountOfCards(){
        DeckOfCards deckOfCards = new DeckOfCards();
        assertEquals(52, deckOfCards.getDeck().size());
    }

    @Test
    public void dealHandReturns_n_AmountOfCards(){
        DeckOfCards deckOfCards = new DeckOfCards();
        assertEquals(52, deckOfCards.dealHand(52).size());
    }

    @Test
    public void cardsThatAreDealtAreRemoved(){
        DeckOfCards deckOfCards = new DeckOfCards();
        List<PlayingCard> dealt = deckOfCards.dealHand(52);
        assertEquals(0, deckOfCards.getDeck().size());
    }

    @Test
    public void dealingMoreThan52Cards(){
        DeckOfCards deckOfCards = new DeckOfCards();
        assertThrows(IllegalArgumentException.class, ()->{
            deckOfCards.dealHand(53);
        });
    }


    @Test
    public void dealingLessThan1Card(){
        DeckOfCards deckOfCards = new DeckOfCards();
        assertThrows(IllegalArgumentException.class, ()->{
            deckOfCards.dealHand(0);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            deckOfCards.dealHand(-1);
        });
    }

    @Test
    public void ultimatelyDealingMoreThan52Cards(){
        DeckOfCards deckOfCards = new DeckOfCards();
        deckOfCards.dealHand(51);
        assertThrows(IllegalArgumentException.class, ()->{
            deckOfCards.dealHand(2);
        });
    }



}
