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
public class CharityController {

    @FXML
    private Button back;
        @FXML
        private Button chattogram;
        @FXML
        private Button dhk;
        @FXML
        private Button sylhet;
        @FXML
        private Button khulna;
        @FXML
        private Button raj;

        @FXML
        protected void onNewScene(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("chattogram.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
        @FXML
        protected void onNewScene1(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dhaka.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
        @FXML
        protected void onNewScene2(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sylhet.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
        @FXML
        protected void onNewScene3(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("khulna.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
        @FXML
        protected void onNewScene5(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rajshahi.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
    @FXML
    protected void onNewScene6(ActionEvent event) throws IOException {
        // Stage myStage = new Stage();
        Node callingButton = (Node) event.getSource();
        Stage myStage = (Stage)callingButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 700);
        myStage.setScene(scene);
        myStage.show();
    }

}

