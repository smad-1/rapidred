package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Log in!");
        stage.setScene(new Scene(root , 950, 700));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

//#C2252C RED
//#FFFFFF WHITE