package com.example.parcial3.Controllers;

import com.example.parcial3.Models.Appointment;
import com.example.parcial3.Repositories.DataStore;
import com.example.parcial3.Models.Doctor;
import com.example.parcial3.Models.Patient;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.beans.property.SimpleStringProperty;

public class AppointmentsController {
    @FXML private ComboBox<Patient> cmbPatient;
    @FXML private ComboBox<Doctor> cmbDoctor;
    @FXML private DatePicker datePicker;
    @FXML private TextField txtTime; // HH:mm
    @FXML private TableView<Appointment> tableAppointments;
    @FXML private TableColumn<Appointment, String> colAPatient;
    @FXML private TableColumn<Appointment, String> colADoctor;
    @FXML private TableColumn<Appointment, String> colADateTime;

    @FXML
    private void initialize() {

        cmbPatient.setItems(DataStore.getInstance().getPatients());
        cmbDoctor.setItems(DataStore.getInstance().getDoctors());

        txtTime.setPromptText("HH:mm");


        if (colAPatient != null) {
            colAPatient.setCellValueFactory(cell ->
                    new SimpleStringProperty(cell.getValue().getPatient() == null ? "" :
                            cell.getValue().getPatient().getName() + " " + cell.getValue().getPatient().getLastName()));
        }
        if (colADoctor != null) {
            colADoctor.setCellValueFactory(cell ->
                    new SimpleStringProperty(cell.getValue().getDoctor() == null ? "" :
                            cell.getValue().getDoctor().getName() + " " + cell.getValue().getDoctor().getLastName()));
        }
        if (colADateTime != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            colADateTime.setCellValueFactory(cell ->
                    new SimpleStringProperty(cell.getValue().getDateTime() == null ? "" :
                            cell.getValue().getDateTime().format(fmt)));
        }

        if (tableAppointments != null) {
            tableAppointments.setItems(DataStore.getInstance().getAppointments());
        }
    }

    @FXML
    private void onSave(javafx.event.ActionEvent event) {
        try {
            Patient p = cmbPatient.getValue();
            Doctor d = cmbDoctor.getValue();
            LocalDate date = datePicker.getValue();
            String timeStr = txtTime.getText();

            if (p == null || d == null || date == null || timeStr == null || timeStr.isBlank()) {
                throw new IllegalArgumentException("All fields are required");
            }

            LocalTime time;
            try {
                time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("H:mm"));
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException("Time must be in HH:mm format");
            }

            LocalDateTime dateTime = LocalDateTime.of(date, time);

            // Validate no double-booking for doctor or patient at the same time
            boolean doctorConflict = DataStore.getInstance().getAppointments().stream()
                    .anyMatch(a -> a.getDoctor() == d && dateTime.equals(a.getDateTime()));
            if (doctorConflict) {
                throw new IllegalArgumentException("This doctor already has an appointment at the selected date and time.");
            }

            boolean patientConflict = DataStore.getInstance().getAppointments().stream()
                    .anyMatch(a -> a.getPatient() == p && dateTime.equals(a.getDateTime()));
            if (patientConflict) {
                throw new IllegalArgumentException("This patient already has an appointment at the selected date and time.");
            }

            Appointment appt = new Appointment(p, d, dateTime);
            DataStore.getInstance().getAppointments().add(appt);

            showInfo("Appointment scheduled successfully");
            clearForm();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    @FXML
    private void onBack(javafx.event.ActionEvent event) throws IOException {
        // If embedded in the main dashboard (no owner), clear center content.
        // If opened as a dialog (has an owner), close the dialog.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.getOwner() == null) {
            DashboardController.clearContentStatic();
        } else {
            stage.close();
        }
    }

    private void clearForm() {
        cmbPatient.getSelectionModel().clearSelection();
        cmbDoctor.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        txtTime.clear();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }



    @FXML
    private void onDelete(javafx.event.ActionEvent event) {
        Appointment selected = tableAppointments == null ? null : tableAppointments.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select an appointment to delete");
            return;
        }
        DataStore.getInstance().getAppointments().remove(selected);
        showInfo("Appointment deleted successfully");
    }
}
