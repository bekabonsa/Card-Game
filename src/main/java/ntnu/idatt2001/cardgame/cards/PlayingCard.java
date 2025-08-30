package ntnu.idatt2001.cardgame.cards;

import java.io.File;

/**
 * Represents a playing card. A playing card has a number (face) between
 * 1 and 13, where 1 is called an Ace, 11 = Knight, 12 = Queen and 13 = King.
 * The card can also be one of 4 suits: Spade, Heart, Diamonds and Clubs.
 * @author ntnu
 * @version 2020-01-10
 */
public class PlayingCard {

    private final char suit; // 'S'=spade, 'H'=heart, 'D'=diamonds, 'C'=clubs
    private final int face; // a number between 1 and 13

    /**
     * Creates an instance of a ntnu.idatt2001.CardsGame.PlayingCard with a given suit and face.
     *
     * @param suit The suit of the card, as a single character. 'S' for Spades,
     *             'H' for Heart, 'D' for Diamonds and 'C' for clubs
     * @param face The face value of the card, an integer between 1 and 13
     */
    public PlayingCard(char suit, int face) {
        this.suit = suit;
        this.face = face;
    }

    /**
     * Returns the suit and face of the card as a string.
     * A 4 of hearts is returned as the string "H4".
     *
     * @return the suit and face of the card as a string
     */
    public String getAsString() {
        return String.format("%s%s", suit, face);
    }

    /**
     * Returns the suit of the card, 'S' for Spades, 'H' for Heart, 'D' for Diamonds and 'C' for Clubs
     *
     * @return the suit of the card
     */
    public char getSuit() {
        return suit;
    }

    /**
     * Returns the face of the card (value between 1 and 13).
     *
     * @return the face of the card
     */
    public int getFace() {
        return face;
    }

    /**
     * takes in an Argument cardName and gives relative path of the corresponding image to that card
     * @param cardName
     * @return String value of absolute path to the arguments picture
     */
    public String getCardImagePath(String cardName){
        return String.format("file:"+"src/main/resources/ntnu/idatt2001/cardgame/cards images/%s", cardName)+".png" ;
    }


    @Override
    public String toString() {
        return "PlayingCard{" +
                "suit=" + suit +
                ", face=" + face +
                '}';
    }
}