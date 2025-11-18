package com.example.parcial3.Controllers;

import com.example.parcial3.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class DashboardController {
    @FXML private StackPane contentArea;

    private static DashboardController instance;

    @FXML
    private void initialize() {
        instance = this;
    }

    private void loadContent(String fxmlPath, String title) {
        try {
            var url = Objects.requireNonNull(App.class.getResource(fxmlPath),
                    () -> "FXML not found: " + fxmlPath);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            contentArea.getChildren().setAll(root);
        } catch (Exception ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Unable to load view (" + title + "): " + ex.getMessage()).showAndWait();
        }
    }

    public void clearContent() {
        contentArea.getChildren().setAll(new Label("Select an action from the left sidebar"));
    }

    public static void clearContentStatic() {
        if (instance != null) {
            instance.clearContent();
        }
    }

    @FXML
    private void onScheduleAppointment(javafx.event.ActionEvent event) {
        loadContent("/com/example/parcial3/appointments.fxml", "Schedule Appointment");
    }

    @FXML
    private void onCreatePatient(javafx.event.ActionEvent event) {
        loadContent("/com/example/parcial3/patients.fxml", "Create Patient");
    }

    @FXML
    private void onCreateDoctor(javafx.event.ActionEvent event) {
        loadContent("/com/example/parcial3/doctors.fxml", "Create Doctor");
    }
}
