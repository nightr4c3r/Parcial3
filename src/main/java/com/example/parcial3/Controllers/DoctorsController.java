package com.example.parcial3.Controllers;

import com.example.parcial3.Repositories.DataStore;
import com.example.parcial3.Models.Doctor;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.cell.PropertyValueFactory;

public class DoctorsController {
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtId;
    @FXML private TextField txtSpecialty;
    @FXML private TableView<Doctor> tableDoctors;
    @FXML private TableColumn<Doctor, String> colDName;
    @FXML private TableColumn<Doctor, String> colDLastName;
    @FXML private TableColumn<Doctor, String> colDID;
    @FXML private TableColumn<Doctor, String> colDSpecialty;

    @FXML
    private void initialize() {
        if (colDName != null) colDName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colDLastName != null) colDLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        if (colDID != null) colDID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        if (colDSpecialty != null) colDSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));

        if (tableDoctors != null) {
            tableDoctors.setItems(DataStore.getInstance().getDoctors());
        }
    }

    @FXML
    private void onSave(javafx.event.ActionEvent event) {
        try {
            String name = txtName.getText();
            String lastName = txtLastName.getText();
            String id = txtId.getText();
            String specialty = txtSpecialty.getText();

            if (name.isBlank() || lastName.isBlank() || id.isBlank() || specialty.isBlank()) {
                throw new IllegalArgumentException("All fields are required");
            }

            Doctor d = new Doctor(name, lastName, id, specialty);
            DataStore.getInstance().getDoctors().add(d);
            showInfo("Doctor saved successfully");
            clearForm();
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    @FXML
    private void onBack(javafx.event.ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.getOwner() == null) {
            DashboardController.clearContentStatic();
        } else {
            stage.close();
        }
    }

    private void clearForm() {
        txtName.clear();
        txtLastName.clear();
        txtId.clear();
        txtSpecialty.clear();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }

    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.showAndWait();
    }



    @FXML
    private void onDelete(javafx.event.ActionEvent event) {
        Doctor selected = tableDoctors == null ? null : tableDoctors.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a doctor to delete");
            return;
        }
        DataStore.getInstance().getDoctors().remove(selected);
        showInfo("Doctor deleted successfully");
    }
}
