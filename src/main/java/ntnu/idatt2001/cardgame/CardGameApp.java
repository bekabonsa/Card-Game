package ntnu.idatt2001.cardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardGameApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CardGameApp.class.getResource("CardGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setMinHeight(500);
        stage.setMinWidth(800);
        stage.setTitle("Card Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}