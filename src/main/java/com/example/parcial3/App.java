package com.example.parcial3.Models;

import com.example.parcial3.Launcher;import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.App.class.getResource("/com/example/parcial3/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Clinic Manager");
        stage.setScene(scene);
        stage.show();
    }
}
