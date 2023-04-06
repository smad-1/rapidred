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

public class BloodBankController implements Initializable {
    @FXML
    private Button button_back;
    @FXML
    private Button button_search;
    @FXML
    private ChoiceBox<String> cb_location;
    private  String[] locations = {"Gazipur","Boardbazar","Uttara","Dhanmondi","Banani","Gulshan","Agargaon","Paltan","Motijheel","Mohammadpur","Lalbagh"};
    @FXML
    private TableView<BloodBanks> table_bloodbank;
    @FXML
    private TableColumn<?,?> col_name;
    @FXML
    private TableColumn<?,?> col_loc;
    @FXML
    private TableColumn<?,?> col_phone;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ObservableList<BloodBanks> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "MySQLPass123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        data = FXCollections.observableArrayList();
        setTable();

        cb_location.getItems().addAll(locations);

        button_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table_bloodbank.getItems().clear();
                if(cb_location.getValue()!=null)
                {
                    findBank(event,cb_location.getValue().toString());
                }
                else //empty fields
                {
                    System.out.println("Please select a location.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please select a location.");
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
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_loc.setCellValueFactory(new PropertyValueFactory<>("location"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void findBank(ActionEvent event, String location)
    {
        try{
            ps = conn.prepareStatement("SELECT name,location,phone FROM bloodbanks WHERE location=?");
            ps.setString(1,cb_location.getValue().toString());
            rs = ps.executeQuery();

            while(rs.next())
            {
                data.add(new BloodBanks(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table_bloodbank.setItems(data);
    }
}
