package com.project.Library_Management_Spring_BackEnd.dto.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {

    private String name;
    private String description;
    private Set<String> permissions;
}
