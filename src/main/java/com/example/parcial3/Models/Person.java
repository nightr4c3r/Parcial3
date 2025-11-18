package com.example.parcial3.Models;

public class Person {
    private String name;
    private String lastName;
    private String ID;

    public Person() {}

    public Person(String name, String lastName, String ID) {
        this.name = name;
        this.lastName = lastName;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + ID;
    }
}
