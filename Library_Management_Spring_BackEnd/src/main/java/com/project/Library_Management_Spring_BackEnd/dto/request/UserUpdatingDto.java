package com.project.Library_Management_Spring_BackEnd.dto.request;

import java.time.LocalDate;

public class UserUpdatingDto {

    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public UserUpdatingDto() {
    }

    public UserUpdatingDto(String password, String firstName, String lastName, LocalDate dob) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
