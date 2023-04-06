package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
        @FXML
        private Button back;
        @FXML
        private Button button_submit;
        @FXML
        private TextArea ta_report;

        Connection conn = null;
        PreparedStatement psInsert = null;

        @FXML
        protected void onNewScene(ActionEvent event) throws IOException {
            // Stage myStage = new Stage();
            Node callingButton = (Node)event.getSource();
            Stage myStage = (Stage)callingButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("report-feedback.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 950, 700);
            myStage.setScene(scene);
            myStage.show();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ta_report.getText().isEmpty()) {
                    System.out.println("Please write your report to submit.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please write your report to submit.");
                    alert.show();

                } else {
                    try {
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");
                        psInsert = conn.prepareStatement("INSERT INTO report (comment) VALUES (?)");
                        psInsert.setString(1, ta_report.getText().toString());
                        psInsert.executeUpdate();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (psInsert != null) {
                            try {
                                psInsert.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //alert

                    System.out.println("Your report has been submitted!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Your report has been submitted!");
                    alert.show();

                    DBUtils.changeScene(event,"report-feedback.fxml","Report/Feedback",null);
                }
            }
        });
    }
}


