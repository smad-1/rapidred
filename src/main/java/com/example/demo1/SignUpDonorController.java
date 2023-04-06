package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignUpDonorController implements Initializable {
    @FXML
    private Button button_back;
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_age;
    @FXML
    private ChoiceBox<String> cb_gender;
    private String[] gender = {"Female", "Male"};
    @FXML
    private ChoiceBox<String> cb_blood;
    private String[] groups = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    @FXML
    private ChoiceBox<String> cb_location;
    private  String[] locations = {"Gazipur","Boardbazar","Uttara","Dhanmondi","Banani","Gulshan","Agargaon","Paltan","Motijheel","Mohammadpur","Lalbagh"};
    @FXML
    private TextField tf_phone;
    @FXML
    private ChoiceBox<String> cb_date;
    private String [] dates = {"< 3 months","> 3 months","Never"};
    @FXML
    private TextField tf_condition;
    @FXML
    private Button button_signup;
   // private String db_username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_gender.getItems().addAll(gender);
        cb_blood.getItems().addAll(groups);
        cb_location.getItems().addAll(locations);
        cb_date.getItems().addAll(dates);

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

               // db_username = setUsername()
                System.out.println(DataSingleton.userName);

                if(!tf_firstName.getText().trim().isEmpty() && !tf_lastName.getText().trim().isEmpty() && !tf_age.getText().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && cb_gender.getValue()!=null && cb_blood.getValue()!=null && cb_location.getValue()!=null && cb_date.getValue()!=null)
                {
                    if(Integer.parseInt(tf_age.getText()) < 18){
                        System.out.println("You have to be above 18 to register as donor.");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("You must be above 18 to be a donor.");
                        alert.show();
                    }
                    else{
                        //  System.out.println("inside else");
                        DBUtils.signUpDonor(event, DataSingleton.userName, Integer.parseInt(tf_age.getText()), tf_phone.getText(), cb_gender.getValue().toString(), cb_blood.getValue().toString(), cb_location.getValue().toString(), cb_date.getValue().toString(), tf_condition.getText());
                    }
                }
                else{
                    System.out.println("Incomplete form");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all the information.");
                    alert.show();
                    //if null alert box
                }

            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"home-page.fxml","Welcome!",DataSingleton.userName);
            }
        });
    }

    public String setUsername(String username){
        return username;
    }
}
