package com.project.Library_Management_Spring_BackEnd.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationDto {

    @NotNull(message = "Username không dược để trống")
    @Size(min = 6, message = "Username có ít hơn 6 ký tự")
    String username;

    @NotNull(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu có ít hơn 6 ký tự")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    List<String> roles;
}
