package com.project.Library_Management_Spring_BackEnd.dto;

import java.time.LocalDate;

public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public UserDto() {
    }

    public UserDto(String username, String firstName, String lastName, LocalDate dob) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
