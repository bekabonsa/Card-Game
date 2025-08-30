module ntnu.idatt2001.cardgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ntnu.idatt2001.cardgame to javafx.fxml;
    exports ntnu.idatt2001.cardgame;
}