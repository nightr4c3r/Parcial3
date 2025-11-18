package com.example.parcial3.Repositories;

import com.example.parcial3.Models.Appointment;
import com.example.parcial3.Models.Doctor;
import com.example.parcial3.Models.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private final ObservableList<Patient> patients = FXCollections.observableArrayList();
    private final ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    private DataStore() {}

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public ObservableList<Patient> getPatients() {
        return patients;
    }

    public ObservableList<Doctor> getDoctors() {
        return doctors;
    }

    public ObservableList<Appointment> getAppointments() {
        return appointments;
    }
}
