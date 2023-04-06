package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
public class AboutBloodController implements Initializable {

    @FXML
    public Button button;
    @FXML
    public ImageView my;

        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
            TranslateTransition translate = new TranslateTransition();

            translate.setNode(my);



            translate.setByX(950);
            translate.setDuration(Duration.millis(1500));
            translate.play();

            TranslateTransition translate1 = new TranslateTransition();

            translate1.setNode(button);



            translate1.setByX(-430);
            translate1.setDuration(Duration.millis(1500));
            translate1.play();


            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    DBUtils.changeScene(event, "home-page.fxml","Welcome!", null);
                }
            });



        }


    }

