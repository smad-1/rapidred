package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable{
    @FXML
    private RadioButton rb_hosA;
    @FXML
    private RadioButton rb_hosB;
    @FXML
    private RadioButton rb_hosC;
    @FXML
    private RadioButton rb_bankA;
    @FXML
    private RadioButton rb_bankB;
    @FXML
    private RadioButton rb_bankC;
    @FXML
    private ToggleGroup place;
    @FXML
    private Button button_next;
    @FXML
    private Button button_back;
    public void handleButton(ActionEvent event) throws IOException {

        if (!rb_hosA.isSelected() && !rb_hosB.isSelected() && !rb_hosC.isSelected() && !rb_bankA.isSelected() &&!rb_bankB.isSelected() && !rb_bankC.isSelected()) {
            System.out.println("Please select a hospital or a blood bank to continue.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a hospital or a blood bank to continue.");
            alert.show();
        }
        else{
            String text = null;

            if (rb_hosA.isSelected()) {
                text = rb_hosA.getText();
            } else if (rb_hosB.isSelected()) {
                text = rb_hosB.getText();
            } else if (rb_hosC.isSelected()) {
                text = rb_hosC.getText();
            } else if (rb_bankA.isSelected()) {
                text = rb_bankA.getText();
            } else if (rb_bankB.isSelected()) {
                text = rb_bankB.getText();
            } else if (rb_bankC.isSelected()) {
                text = rb_bankC.getText();
            }
            System.out.println(text);

            Parent root = null;
            FXMLLoader loader =  new FXMLLoader(HelloApplication.class.getResource("appointment-two.fxml"));
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            AppointmentTwoController twoController = loader.getController();
            twoController.setHospital(text);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Make Appointment");
            stage.setScene(new Scene(root, 950, 700));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"home-page.fxml","Welcome!",null);
            }
        });
    }
}
