package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentTwoController implements Initializable {
    @FXML
    private Label label_hospital;
    @FXML
    private Button button_back;
    @FXML
    private TextField tf_firstname;
    @FXML
    private TextField tf_lastname;
    @FXML
    private DatePicker date_birth;
    @FXML
    private ChoiceBox<String> cb_blood;
    private String[] groups = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    @FXML
    private ChoiceBox<String> cb_gender;
    private String[] gender = {"Female", "Male"};
    @FXML
    private TextField tf_occupation;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_address;
    @FXML
    private ChoiceBox<String> cb_lastdonated;
    private String [] dates = {"< 3 months","> 3 months","Never"};
    @FXML
    private RadioButton rb1_yes, rb1_no;
    @FXML
    private ToggleGroup tog1;
    @FXML
    private RadioButton rb2_yes, rb2_no;
    @FXML
    private ToggleGroup tog2;
    @FXML
    private RadioButton rb3_yes, rb3_no;
    @FXML
    private ToggleGroup tog3;
    @FXML
    private RadioButton rb4_yes, rb4_no;
    @FXML
    private ToggleGroup tog4;
    @FXML
    private RadioButton rb5_yes, rb5_no;
    @FXML
    private ToggleGroup tog5;
    @FXML
    private RadioButton rb6_yes, rb6_no;
    @FXML
    private ToggleGroup tog6;
    @FXML
    private RadioButton rb7_yes, rb7_no;
    @FXML
    private ToggleGroup tog7;
    @FXML
    private DatePicker date_app;
    @FXML
    private Button button_submit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cb_gender.getItems().addAll(gender);
        cb_blood.getItems().addAll(groups);
        cb_lastdonated.getItems().addAll(dates);

        // see if all fields are not empty

        button_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!checkIfEmpty()){
                    //can submit
                    LocalDate selectedDate = date_birth.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dob = selectedDate.format(formatter);
                    System.out.println(dob);

                    LocalDate selectedDate2 = date_app.getValue();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String appdate = selectedDate2.format(formatter2);
                    System.out.println(appdate);

                    String s1,s2,s3,s4,s5,s6,s7;
                    if(rb1_yes.isSelected()) s1="YES";
                    else s1="NO";
                    if(rb2_yes.isSelected()) s2="YES";
                    else s2="NO";
                    if(rb3_yes.isSelected()) s3="YES";
                    else s3="NO";
                    if(rb4_yes.isSelected()) s4="YES";
                    else s4="NO";
                    if(rb5_yes.isSelected()) s5="YES";
                    else s5="NO";
                    if(rb6_yes.isSelected()) s6="YES";
                    else s6="NO";
                    if(rb7_yes.isSelected()) s7="YES";
                    else s7="NO";

                    try {
                        DBUtils.submitForm(event,label_hospital.getText().toString(),tf_firstname.getText(),tf_lastname.getText(),dob,cb_blood.getValue().toString(),cb_gender.getValue().toString(),tf_occupation.getText(),tf_phone.getText(),tf_email.getText(),tf_address.getText(),cb_lastdonated.getValue().toString(),s1,s2,s3,s4,s5,s6,s7,appdate);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("Your form has been submitted successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Your form has been submitted successfully. Please bring your past medical reports on the day of appointment.");
                    alert.show();

                    DBUtils.changeScene(event,"home-page.fxml","Welcome!",null);

                }
                else{
                    //error
                    System.out.println("Please fill up all the information.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("You must fill up all the information to continue.");
                    alert.show();
                }
            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"appointment.fxml","Welcome!",null);
            }
        });


       // label_hospital.setText("Hospital: " + DataSingleton.place_chosen);
    }

    public boolean checkIfEmpty(){

        if(!tf_firstname.getText().trim().isEmpty() && !tf_lastname.getText().trim().isEmpty() && date_birth.getValue()!=null && cb_gender.getValue()!=null && cb_blood.getValue()!=null && !tf_occupation.getText().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_email.getText().trim().isEmpty() && !tf_address.getText().trim().isEmpty() && cb_lastdonated.getValue()!=null && date_app.getValue()!=null){
            if((rb1_yes.isSelected() || rb1_no.isSelected()) && (rb2_yes.isSelected() || rb2_no.isSelected()) && (rb3_yes.isSelected() || rb3_no.isSelected()) && (rb4_yes.isSelected() || rb4_no.isSelected()) && (rb5_yes.isSelected() || rb5_no.isSelected()) && (rb6_yes.isSelected() || rb6_no.isSelected()) && (rb7_yes.isSelected() || rb7_no.isSelected())){
                return false;
            }
        }
        return true;
    }

    public void setHospital(String hospital){
        label_hospital.setText("Hospital:      " + hospital);
    }
}
