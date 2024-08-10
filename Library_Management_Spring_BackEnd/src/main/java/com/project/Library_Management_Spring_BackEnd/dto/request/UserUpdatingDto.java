package com.project.Library_Management_Spring_BackEnd.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdatingDto {

    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    List<String> roles;
}
