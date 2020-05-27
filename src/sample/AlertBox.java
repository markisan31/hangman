package sample;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class AlertBox {

    public void display(Boolean win, String word, String definition){
        Stage window = new Stage();


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Mõiste ja selle definistioon");
        window.setMinWidth(300);
        window.setMinHeight(300);
        window.setMaxWidth(400);
        window.setMaxHeight(400);
        window.setX(500);
        window.setY(330);



        Label winLabel = new Label();
        Label looseLabel = new Label();
        winLabel.setText("Palju õnne! Leitud mõiste definitsioon on: ");
        looseLabel.setText("Ära muretse, järgmine kord sa kindlasti võidad=) Peidetud mõiste oli: "+
                "\"" + word + "\"" + "\nJa selle definitsioon: ");
        winLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        winLabel.setTextFill(Color.GREEN);


        looseLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        looseLabel.setTextFill(Color.RED);
        looseLabel.setWrapText(true);
        looseLabel.setTextAlignment(TextAlignment.JUSTIFY);

        Label label = new Label();
        label.setTextFill(Color.BLUEVIOLET);
        label.setText("\"" + definition + "\"");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        Button closeButton = new Button("Pane kinni");
        closeButton.setOnAction(e ->

                window.close());

        VBox layout = new VBox(20);
        if (win == true) {
            layout.getChildren().addAll(winLabel, label, closeButton);
        } else if (win == false){
            layout.getChildren().addAll(looseLabel, label, closeButton);
        }

        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}
