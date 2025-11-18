package com.example.parcial3.Models;

public class Doctor extends Person {
    private String specialty;


    public Doctor() {}

    public Doctor(String name, String lastName, String ID, String specialty) {
        super(name, lastName, ID);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return super.toString() + " " + specialty;
    }
}
