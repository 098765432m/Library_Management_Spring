package com.project.Library_Management_Spring_BackEnd.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserCreationDto {

    @NotNull(message = "Username không dược để trống")
    @Size(min = 6, message = "Username có ít hơn 6 ký tự")
    private String username;

    @NotNull(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu có ít hơn 6 ký tự")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public String getUsername() {
        return username;
    }

    public UserCreationDto(String username, String password, String firstName, String lastName, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public void setUsername(String username) {
        this.username = username;
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
