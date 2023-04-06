package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class RajshahiController {
        @FXML
        private Button back;


        @FXML
        protected void onNewScene1(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node)event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("charity.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
    }

