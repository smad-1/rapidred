package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;


import java.io.IOException;
public class AppWorksController {
        @FXML
        private ScrollPane scrollPane;

        @FXML
        private ImageView imageView;
        @FXML
        private Button back;

        @FXML
        protected void onNewScene(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node)event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
    }

