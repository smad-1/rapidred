package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
public class ReportFeedbackController {
        @FXML
        private Button feedback;//scene
        @FXML
        private Button report;//scene1
        @FXML
        private  Button back;
        @FXML
        protected void onNewScene(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("feedback.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
        @FXML
        protected void onNewScene1(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("report.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }
        @FXML
        protected void onNewScene2(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node) event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }



    }

