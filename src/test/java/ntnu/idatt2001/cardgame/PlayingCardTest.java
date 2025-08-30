package ntnu.idatt2001.cardgame;
import ntnu.idatt2001.cardgame.cards.PlayingCard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class PlayingCardTest {
    @Test
    public void getCardImagePathReturnsCorrectPath(){
        PlayingCard playingCard = new PlayingCard('C', 1);
        String correctPath = "file:src/main/resources/ntnu/idatt2001/cardgame/cards images/C1.png";
        String calculatedPath = playingCard.getCardImagePath("C1");
        assertEquals(correctPath, calculatedPath);

    }
}
