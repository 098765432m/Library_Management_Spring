package com.project.Library_Management_Spring_BackEnd.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @NotNull
    @Size(min = 3, message = "Username ít hơn 3 ký tự")
    String username;

    @NotNull
    @Size(min = 6, message = "Mật khẩu ít hơn 6 ký tự")
    String password;

}
