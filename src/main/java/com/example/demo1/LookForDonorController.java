package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LookForDonorController implements Initializable {
    @FXML
    private Button button_back;
    @FXML
    private ChoiceBox<String> cb_blood;
    private String[] groups = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
    @FXML
    private ChoiceBox<String> cb_location;
    private  String[] locations = {"Gazipur","Boardbazar","Uttara","Dhanmondi","Banani","Gulshan","Agargaon","Paltan","Motijheel","Mohammadpur","Lalbagh"};
    @FXML
    private Button button_search;
    @FXML
    private TableView<Donors> table_donorinfo;
    @FXML
    private TableColumn<?,?> col_fname;
    @FXML
    private TableColumn<?,?> col_lname;
    @FXML
    private TableColumn<?,?> col_bg;
    @FXML
    private TableColumn<?,?> col_location;
    @FXML
    private TableColumn<?,?> col_phone;
    @FXML
    private TableColumn<?,?> col_eligible;

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ObservableList<Donors> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        data = FXCollections.observableArrayList();
        setTable();

        cb_blood.getItems().addAll(groups);
        cb_location.getItems().addAll(locations);

        button_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table_donorinfo.getItems().clear();
                if(cb_blood.getValue()!=null && cb_location.getValue()!=null)
                {
                    try {
                        lookForDonor(event, cb_blood.getValue().toString(), cb_location.getValue().toString());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                else //empty fields
                {
                    System.out.println("Please select a blood group and location.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please select a blood group and location.");
                    alert.show();
                }
            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"home-page.fxml","Welcome!",null);
            }
        });

    }
    private void setTable()
    {
        col_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_bg.setCellValueFactory(new PropertyValueFactory<>("blood"));
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_eligible.setCellValueFactory(new PropertyValueFactory<>("eligible"));
    }
    private void lookForDonor(ActionEvent event, String blood, String location) throws SQLException {
        try {
            ps = conn.prepareStatement("SELECT firstName,lastName,bloodGroup,location,phone,ifEligible FROM donors WHERE bloodGroup=? AND location=?");
            ps.setString(1,cb_blood.getValue().toString());
            ps.setString(2,cb_location.getValue().toString());
            rs = ps.executeQuery();

            while(rs.next())
            {
                String elig;
                if(rs.getBoolean(6)==true) elig="YES";
                else elig="NO";
                data.add(new Donors(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),elig));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table_donorinfo.setItems(data);

    }
}
