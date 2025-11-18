package com.example.parcial3.Models;

import com.example.parcial3.Models.Doctor;
import com.example.parcial3.Models.Patient;

import java.time.LocalDateTime;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }



    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Patient setPatient(Patient patient) {
        return this.patient = patient;
    }

    public Doctor setDoctor(Doctor doctor) {
        return this.doctor = doctor;
    }

    public LocalDateTime setDateTime(LocalDateTime dateTime) {
        return this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return patient + " " + doctor + " " + dateTime;
    }
}
