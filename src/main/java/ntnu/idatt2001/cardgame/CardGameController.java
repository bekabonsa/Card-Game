package ntnu.idatt2001.cardgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import ntnu.idatt2001.cardgame.cards.PlayingCard;
import ntnu.idatt2001.cardgame.cards.PlayingHand;
import ntnu.idatt2001.cardgame.cards.DeckOfCards;

import java.io.File;
import java.util.ArrayList;

public class CardGameController {
    private DeckOfCards deckOfCards= new DeckOfCards();
    private PlayingHand playingHand = new PlayingHand();
    private PlayingCard playingCard = new PlayingCard('S', 1);
    private int dealSize = 6;


    @FXML private Label welcomeText;
    @FXML private Label cardsDisplay;
    @FXML private ImageView Image1;
    @FXML private ImageView Image2;
    @FXML private ImageView Image3;
    @FXML private ImageView Image4;
    @FXML private ImageView Image5;
    @FXML private ImageView Image6;
    @FXML private Label flush;
    @FXML private Label queenOfSpades;
    @FXML private Label facesSum;
    @FXML private Label cardsOfHearts;
    @FXML private Rectangle queenOfSpadesFlag;
    @FXML private Rectangle flushFlag;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /**
     * logic for deal hand button in the ui.
     * deals a total of dealSize (an attibute of the class with an int value) amount of cards to hand.
     * sets the cardsDisplay label text value to a String formatted version of your hand.
     * To make the game simple and interesting we limit a hand size to 5 cards
     * note-1: If you have 5 cards in your hand already while using dealHand, this method will remove old cards and deal -
     * 5 new cards
     * note-2: If your deck has less than dealSize(default 5) then you deal remaining cards in deck
     * @param event
     */
    @FXML
    private void onDealHandButtonClick(ActionEvent event){

        if(deckOfCards.getDeck().size() < 5){
            dealSize = deckOfCards.getDeck().size();
        }
        if(playingHand.getHand().size() == dealSize){
            playingHand.setHand(new ArrayList<>());
        }
        if(deckOfCards.getDeck().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Illegal move");
            alert.setHeaderText("Your deck is empty");
            alert.setContentText("Game over buddy, better luck next time :)");
            alert.showAndWait();
            return;
        }
        //add dealSize amount of cards to hand
        playingHand.addToHand(deckOfCards.dealHand(dealSize));

        String[] hand = playingHand.stringifyHand().split(" ");
       //adding the images to screen
        Image1.setImage(new Image(playingCard.getCardImagePath(hand[0])));
        Image2.setImage(new Image(playingCard.getCardImagePath(hand[1])));
        Image3.setImage(new Image(playingCard.getCardImagePath(hand[2])));
        Image4.setImage(new Image(playingCard.getCardImagePath(hand[3])));
        Image5.setImage(new Image(playingCard.getCardImagePath(hand[4])));
        Image6.setImage(new Image(playingCard.getCardImagePath(hand[5])));
    }

    @FXML
    private void onCheckHandButtonClick(ActionEvent event){
        //checking all hand checkers in PlayingHand
        playingHand.checkHand();
        //setting values to be displayed
        facesSum.setText(String.valueOf(playingHand.getFaceSum()));
        queenOfSpades.setText(playingHand.hasQueenOfSpades() ? "yes" : "none");
        cardsOfHearts.setText(playingHand.getCardsOfHearts());
        flush.setText(playingHand.hasFlush() ? "yes" : "none");
        //setting flag value to red before we check

        queenOfSpadesFlag.setStyle("-fx-fill:" + "red" + ";");
        flushFlag.setStyle("-fx-fill:" + "red" + ";");

        //set flag to green if boolean is true
        if(playingHand.hasFlush()){
            flushFlag.setStyle("-fx-fill:" + "green" + ";");
        }
        if(playingHand.hasQueenOfSpades()){
            queenOfSpadesFlag.setStyle("-fx-fill:" + "green" + ";");
        }


    }
}