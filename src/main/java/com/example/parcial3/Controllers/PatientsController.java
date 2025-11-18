package com.example.parcial3.Controllers;

import com.example.parcial3.Repositories.DataStore;
import com.example.parcial3.Models.Patient;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.cell.PropertyValueFactory;

public class PatientsController {
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtId;
    @FXML private TextField txtAge;
    @FXML private TextField txtAddress;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, String> colPName;
    @FXML private TableColumn<Patient, String> colPLastName;
    @FXML private TableColumn<Patient, String> colPID;
    @FXML private TableColumn<Patient, Integer> colPAge;
    @FXML private TableColumn<Patient, String> colPAddress;

    @FXML
    private void initialize() {

        if (colPName != null) colPName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colPLastName != null) colPLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        if (colPID != null) colPID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        if (colPAge != null) colPAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        if (colPAddress != null) colPAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        if (tablePatients != null) {
            tablePatients.setItems(DataStore.getInstance().getPatients());
        }
    }

    @FXML
    private void onSave(javafx.event.ActionEvent event) {
        try {
            String name = txtName.getText();
            String lastName = txtLastName.getText();
            String id = txtId.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAddress.getText();

            if (name.isBlank() || lastName.isBlank() || id.isBlank()) {
                throw new IllegalArgumentException("Name, Last Name and ID are required");
            }

            Patient p = new Patient(name, lastName, id, age, address);
            DataStore.getInstance().getPatients().add(p);
            showInfo("Patient saved successfully");
            clearForm();
        } catch (NumberFormatException ex) {
            showError("Age must be a number");
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
        txtAge.clear();
        txtAddress.clear();
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
        Patient selected = tablePatients == null ? null : tablePatients.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a patient to delete");
            return;
        }
        DataStore.getInstance().getPatients().remove(selected);
        showInfo("Patient deleted successfully");
    }
}
