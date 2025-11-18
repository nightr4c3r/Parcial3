package com.example.parcial3.Models;

public class Patient extends Person {
    private int age;
    private String address;


    public Patient() {}

    public Patient(String name, String lastName, String ID, int age, String address) {
        super(name, lastName, ID);
        this.age = age;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + " " + age + " " + address;
    }
}
