package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pass_field;
    @FXML
    private PasswordField confirm_field;
    @FXML
    private TextField tf_nid;
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !pass_field.getText().trim().isEmpty() && pass_field.getText().toString().equals(confirm_field.getText().toString()) && !tf_nid.getText().trim().isEmpty() && !tf_firstName.getText().trim().isEmpty() && !tf_lastName.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event, tf_username.getText(), pass_field.getText(), Integer.parseInt(tf_nid.getText()), tf_firstName.getText(), tf_lastName.getText());
                }
                else{
                    System.out.println("Please fill in all information.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up.");
                    alert.show();
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"hello-view.fxml", "Log in!", null);
            }
        });
    }
}
