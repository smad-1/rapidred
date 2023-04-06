package com.example.demo1;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import  javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_donate;
    @FXML
    private Button button_find_donor;
    @FXML
    private Button button_bloodbank;
    @FXML
    private Button button_charity;
    @FXML
    private Button button_about;
    @FXML
    private Button button_appworks;
    @FXML
    private Button button_form;
    @FXML
    private Button button_repfeed;
    @FXML
    private Label label_welcome;
    DataSingleton data = DataSingleton.getInstance();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TranslateTransition translate0 = new TranslateTransition();

        translate0.setNode(button_logout);



        translate0.setByX(100);
        translate0.setDuration(Duration.millis(1500));
        translate0.play();

        TranslateTransition translate1 = new TranslateTransition();

        translate1.setNode(button_donate);



        translate1.setByX(100);
        translate1.setDuration(Duration.millis(1500));
        translate1.play();

        TranslateTransition translate2 = new TranslateTransition();

        translate2.setNode(button_about);



        translate2.setByX(-100);
        translate2.setDuration(Duration.millis(1500));
        translate2.play();

        TranslateTransition translate3 = new TranslateTransition();

        translate3.setNode(button_appworks);



        translate3.setByX(-100);
        translate3.setDuration(Duration.millis(1500));
        translate3.play();

        TranslateTransition translate4 = new TranslateTransition();

        translate4.setNode(button_bloodbank);



        translate4.setByX(100);
        translate4.setDuration(Duration.millis(1500));
        translate4.play();

        TranslateTransition translate5 = new TranslateTransition();

        translate5.setNode(button_charity);



        translate5.setByX(-100);
        translate5.setDuration(Duration.millis(1500));
        translate5.play();

        TranslateTransition translate6 = new TranslateTransition();

        translate6.setNode(button_form);



        translate6.setByX(100);
        translate6.setDuration(Duration.millis(1500));
        translate6.play();


        TranslateTransition translate7 = new TranslateTransition();

        translate7.setNode(button_repfeed);



        translate7.setByX(-100);
        translate7.setDuration(Duration.millis(1500));
        translate7.play();



        TranslateTransition translate8 = new TranslateTransition();

        translate8.setNode(button_find_donor);



        translate8.setByX(100);
        translate8.setDuration(Duration.millis(1500));
        translate8.play();



        TranslateTransition translate9 = new TranslateTransition();

        translate9.setNode(label_welcome);



        translate9.setByY(100);
        translate9.setDuration(Duration.millis(1500));
        translate9.play();


        System.out.println(data.userName);
        label_welcome.setText("Welcome " + data.userName + "!");

         button_logout.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 DBUtils.changeScene(event, "hello-view.fxml", "Log in!", null);
             }
            });

         button_donate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "signup-donor.fxml", "Donor Registration", data.userName);
            }
        });

        button_find_donor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "look-for-donor.fxml","Find Donor!",null);
            }
        });

        button_bloodbank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"blood-bank.fxml","Blood Banks", null);
            }
        });

        button_charity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"charity.fxml","Charity",null);
            }
        });

        button_about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"about-blood.fxml","About Blood Donations", null);
            }
        });

        button_appworks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "app-works.fxml", "How App Works", null);
            }
        });

        button_form.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "appointment.fxml", "Make Appointment", null);
            }
        });

        button_repfeed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "report-feedback.fxml","Report/Feedback",null);
            }
        });

    }

    public void setUserInformation(String username){
        label_welcome.setText("Welcome " + username + "!");
    }
}