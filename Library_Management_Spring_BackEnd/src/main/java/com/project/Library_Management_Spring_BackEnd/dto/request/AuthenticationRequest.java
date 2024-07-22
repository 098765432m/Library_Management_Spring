package com.project.Library_Management_Spring_BackEnd.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {

    @NotNull
    @Size(min = 3, message = "Username ít hơn 3 ký tự")
    private String username;

    @NotNull
    @Size(min = 6, message = "Mật khẩu ít hơn 6 ký tự")
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public @NotNull @Size(min = 3, message = "Username ít hơn 3 ký tự") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull @Size(min = 3, message = "Username ít hơn 3 ký tự") String username) {
        this.username = username;
    }

    public @NotNull @Size(min = 6, message = "Mật khẩu ít hơn 6 ký tự") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 6, message = "Mật khẩu ít hơn 6 ký tự") String password) {
        this.password = password;
    }
}
